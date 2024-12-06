package com.example.demo.repository;

import com.example.demo.models.Task;
import com.example.demo.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAuthorOrAssignee(User author, User assignee, Pageable pageable);
}