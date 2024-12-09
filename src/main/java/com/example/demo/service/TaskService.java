//package com.example.demo.service;
//
//import com.example.demo.models.HttpException;
//import com.example.demo.models.Task;
//import com.example.demo.models.User;
//import com.example.demo.repository.TaskRepository;
//import com.example.demo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class TaskService {
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
////    public Page<Task> getTasksForUser(User user, Pageable pageable) {
////        return taskRepository.findByAuthorOrAssignee(user, user, pageable);
////    }
//
//    public Task createTask(Task task) {
//        return taskRepository.save(task);
//    }
//
//}
