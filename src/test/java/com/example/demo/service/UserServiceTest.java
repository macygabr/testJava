package com.example.demo.service;

import com.example.demo.exceptions.GlobalExceptionHandler;
import com.example.demo.models.user.Role;
import com.example.demo.models.user.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    private static final List<User> users = new ArrayList<>();

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
        userRepository.deleteAll();
    }

    @Test
    void testCreateSuccess() {
        User user = User.builder()
                .email("bob@example.com")
                .password("bob")
                .role(Role.ROLE_ADMIN)
                .build();
        userService.create(user);
    }

    @Test
    void testCreateFail() {
        Random random = new Random();
        int randomIndex = random.nextInt(users.size());

        assertThrows(GlobalExceptionHandler.HttpException.class, () -> {
            userService.create(users.get(randomIndex));
        });
    }

    @Test
    void testGetByUsernameSuccess() {
        Random random = new Random();
        int randomIndex = random.nextInt(users.size());
        userService.getByUsername(users.get(randomIndex).getUsername());
    }

    @Test
    void testGetByUsernameFail() {
        assertThrows(GlobalExceptionHandler.HttpException.class, () -> {
            userService.getByUsername("tr45t$gfmr.teg");
        });
    }

    @Test
    void testDeleteSuccess() {
        Random random = new Random();
        int randomIndex = random.nextInt(users.size());
        userService.deleteUser(users.get(randomIndex).getUsername());
    }

    @Test
    void testDeleteFail() {
        assertThrows(GlobalExceptionHandler.HttpException.class, () -> {
            userService.deleteUser("tr45t$gfmr.teg");
        });
    }

    @Test
    @WithMockUser(username = "test1@example.com", roles = "USER")
    void testCurrentUserSuccess() {
        User user = userService.getCurrentUser();
        assertEquals(user, userService.getByUsername("test1@example.com"));
    }

    @Test
    void testCurrentUserFail() {
        assertThrows(Exception.class, () -> {
            userService.getCurrentUser();
        });
    }
}


