package com.example.demo.service;

import com.example.demo.exceptions.GlobalExceptionHandler;
import com.example.demo.models.task.Priority;
import com.example.demo.models.task.Status;
import com.example.demo.models.task.Task;
import com.example.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public Task createTask(String title, String description, Status status, Priority priority, String assignee) {
        Task task = Task.builder()
                .title(title)
                .description(description)
                .status(status)
                .priority(priority)
                .author(userService.getCurrentUser())
                .assignee(userService.getByUsername(assignee))
                .build();
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) {
        Task task = getTask(id);
        taskRepository.deleteById(task.getId());
    }

    public void changeStatus(Long id, Status status) {
        Task task = getTask(id);
        task.setStatus(status);
        taskRepository.save(task);
    }

    public void changePriority(Long id, Priority priority) {
        Task task = getTask(id);
        task.setPriority(priority);
        taskRepository.save(task);
    }

    public void setAssignee(Long id, String assignee) {
        Task task = getTask(id);
        task.setAssignee(userService.getByUsername(assignee));
        taskRepository.save(task);
    }

    public void addComment(Long id, String comment) {
        Task task = getTask(id);
        task.getComments().add(comment);
    }
    public Task change(Long id, String title, String description, Status status, Priority priority, String assignee) {
        Task task = getTask(id);
        Task newTask = Task.builder()
                .title(title)
                .description(description)
                .status(status)
                .priority(priority)
                .assignee(userService.getByUsername(assignee))
                .build();

        task.copy(newTask);
        return taskRepository.save(task);
    }

    private Task getTask(Long id){
        return  taskRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptionHandler.HttpException(HttpStatus.NOT_FOUND, "Задача не найдена"));
    }

}
