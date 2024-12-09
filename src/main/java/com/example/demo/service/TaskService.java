package com.example.demo.service;

import com.example.demo.models.task.Priority;
import com.example.demo.models.task.Status;
import com.example.demo.models.task.Task;
import com.example.demo.models.user.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public Task createTask(String title, String description, Status status, Priority priority, String assignee) {
        Task task = Task.builder().build();
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) {
    }

    public void changeStatus(Long id, Status status) {
    }

    public void changePriority(Long id, Priority priority) {
    }

    public void setAssignee(Long id, String assignee) {
    }

    public void addComment(Long id, String comment) {
    }
}
