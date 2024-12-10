package com.example.demo.service;

import com.example.demo.exceptions.GlobalExceptionHandler;
import com.example.demo.models.task.Comment;
import com.example.demo.models.task.Priority;
import com.example.demo.models.task.Status;
import com.example.demo.models.task.Task;
import com.example.demo.models.user.Role;
import com.example.demo.models.user.User;
import com.example.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public Task createTask(String title, String description, Status status, Priority priority, String assignee) {
        checkPermission();

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
        checkPermission();

        Task task = getTask(id);
        User currentUser = userService.getCurrentUser();

        boolean isAdmin = currentUser.getRole() == Role.ROLE_ADMIN;
        boolean isAssignee = task.getAssignee().equals(currentUser);

        if (isAdmin || isAssignee) {
            task.setStatus(status);
            taskRepository.save(task);
        } else {
            throw new GlobalExceptionHandler.HttpException(
                    HttpStatus.FORBIDDEN,
                    "Недостаточно прав для изменения статуса задачи"
            );
        }
    }


    public void changePriority(Long id, Priority priority) {
        checkPermission();
        Task task = getTask(id);
        task.setPriority(priority);
        taskRepository.save(task);
    }

    public void setAssignee(Long id, String assignee) {
        checkPermission();
        Task task = getTask(id);
        task.setAssignee(userService.getByUsername(assignee));
        taskRepository.save(task);
    }

    public void addComment(Long id, String content) {
        Task task = getTask(id);
        User currentUser = userService.getCurrentUser();
        boolean isAdmin = currentUser.getRole() == Role.ROLE_ADMIN;
        boolean isAssignee = task.getAssignee().equals(currentUser);
        if (isAdmin || isAssignee) {
            Comment comment = Comment.builder()
                    .content(content)
                    .author(userService.getCurrentUser())
                    .task(task)
                    .build();
            task.getComments().add(comment);
            taskRepository.save(task);
        } else {
            throw new GlobalExceptionHandler.HttpException(
                    HttpStatus.FORBIDDEN,
                    "Недостаточно прав для добавления комментария"
            );
        }
    }
    public Task change(Long id, String title, String description, Status status, Priority priority, String assignee) {
        checkPermission();
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

    private void checkPermission(){
        User author = userService.getCurrentUser();
        if(author.getRole() == Role.ROLE_USER){
            throw new GlobalExceptionHandler.HttpException(HttpStatus.FORBIDDEN, "Недостаточно прав");
        }
    }
}
