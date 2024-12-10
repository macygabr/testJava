package com.example.demo.service;

import com.example.demo.models.task.Priority;
import com.example.demo.models.task.Status;
import com.example.demo.models.task.Task;
import com.example.demo.models.user.Role;
import com.example.demo.models.user.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TaskServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    private static final List<User> users = new ArrayList<>();
    private static final List<Task> tasks = new ArrayList<>();

    @BeforeAll
    static void addUsers(){
        for(int i = 0; i<3; i++){
            User user = User.builder()
                    .email("test"+i+"@example.com")
                    .password("test")
                    .role(Role.ROLE_USER)
                    .build();
            users.add(user);
        }

        User user = User.builder()
                .email("admin@example.com")
                .password("admin")
                .role(Role.ROLE_ADMIN)
                .build();
        users.add(user);
    }

    @BeforeEach
    void init() {
        for (User user: users) {
            userRepository.save(user);
        }
    }

    @AfterEach
    void destroy(){
        taskRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    @WithMockUser(username = "admin@example.com", roles = "ADMIN")
    void testCreateTaskSuccess() throws Exception {
        String title = "";
        String description = "";
        String assignee = "test1@example.com";
        Status status = Status.PENDING;
        Priority priority = Priority.LOW;

        Task createTask = taskService.createTask(title, description, status, priority, assignee);
        Optional<Task> findTask = taskRepository.findByTitle(title);

        assertEquals(createTask.getId(), findTask.get().getId());
    }

    @Test
    @WithMockUser(username = "test1@example.com", roles = "USER")
    void testCreateTaskError() throws Exception {
        String title = "";
        String description = "";
        String assignee = "test0@example.com";
        Status status = Status.PENDING;
        Priority priority = Priority.LOW;

        assertThrows(Exception.class, () -> {
         taskService.createTask(title, description, status, priority, assignee);
        });
    }
}