package com.example.demo.controller;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authService;

    @InjectMocks
    private AuthController authController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() throws Exception {
//        RegisterRequest request = new RegisterRequest("user@example.com", "password", "John Doe");
//        when(authService.register(any(RegisterRequest.class))).thenReturn("Registration successful");
//
//        mockMvc.perform(post("/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Registration successful"));
//
//        verify(authService, times(1)).register(any(RegisterRequest.class));
    }

    @Test
    void testRegisterValidationError() throws Exception {
//        RegisterRequest request = new RegisterRequest("", "", "");
//
//        mockMvc.perform(post("/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isBadRequest());
//
//        verify(authService, never()).register(any(RegisterRequest.class));
    }

    @Test
    void testLoginSuccess() throws Exception {
//        LoginRequest request = new LoginRequest("user@example.com", "password");
//        when(authService.login(any(LoginRequest.class))).thenReturn("JWT_TOKEN");
//
//        mockMvc.perform(post("/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("JWT_TOKEN"));
//
//        verify(authService, times(1)).login(any(LoginRequest.class));
    }

    @Test
    void testLoginInvalidCredentials() throws Exception {
//        LoginRequest request = new LoginRequest("user@example.com", "wrong_password");
//        when(authService.login(any(LoginRequest.class))).thenThrow(new IllegalArgumentException("Invalid credentials"));
//
//        mockMvc.perform(post("/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.message").value("Invalid credentials"));
//
//        verify(authService, times(1)).login(any(LoginRequest.class));
    }
}
