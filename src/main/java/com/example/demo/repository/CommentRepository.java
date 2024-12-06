package com.example.demo.repository;

import com.example.demo.models.Comment;
import com.example.demo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTask(Task task);
}