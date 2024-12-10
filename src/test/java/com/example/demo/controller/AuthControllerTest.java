//package com.example.demo.controller;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import com.example.demo.dto.JwtAuthenticationResponse;
//import com.example.demo.dto.SignInRequest;
//import com.example.demo.dto.SignUpRequest;
//import com.example.demo.models.user.Role;
//import com.example.demo.models.user.User;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.service.AuthenticationService;
//import com.example.demo.service.JwtService;
//import com.example.demo.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Optional;
//
//@WebMvcTest(AuthController.class)
//public class AuthControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private AuthController authController;
//    @Mock
//    private AuthenticationService authService;
//    @Mock
//    private JwtService jwtService;
//    @Mock
//    private UserService userService;
//
//    @BeforeEach
//    void setup() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
//    }
//
//    @Test
//    void testLoginSuccess() throws Exception {
//        String username = "user@example.com";
//        User mockUser = User.builder()
//                .id(1L)
//                .email(username)
//                .password("password")
//                .role(Role.ROLE_USER)
//                .build();
//
//        when(userService.getByUsername(username)).thenReturn(mockUser);
//
//
//        mockMvc.perform(post("/auth/sign-in")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"email\": \"user@example.com\", \"password\": \"password\"}"));
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.token").value("token"));
//
////        verify(authService, times(1)).signIn(new SignInRequest("user@example.com","password"));
//    }
//
////    @Mock
////    private AuthenticationService authService;
////
////    @InjectMocks
////    private AuthController authController;
////
////    @Test
////    void testRegisterSuccess() {
////        SignInRequest request = new SignInRequest("user@example.com", "password");
////        when(authService.signIn(any(SignInRequest.class))).thenReturn(new JwtAuthenticationResponse("token"));
////
////        JwtAuthenticationResponse response = authController.signIn(request);
////
////        verify(authService, times(1)).signIn(request);
////        assertNotNull(response);
////    }
////
////    @Test
////    void testLoginSuccess() {
////        SignUpRequest request = new SignUpRequest("user@example.com", "password");
////        when(authService.signUp(any(SignUpRequest.class))).thenReturn(new JwtAuthenticationResponse("token"));
////
////        JwtAuthenticationResponse response = authController.signUp(request);
////
////        verify(authService, times(1)).signUp(request);
////        assertNotNull(response);
////    }
////
////    @Test
////    void testRegisterValidationError() {
////        LoginRequest request = new LoginRequest("user@example.com", "password");
////        when(authService.login(any(LoginRequest.class))).thenReturn("JWT_TOKEN");
////
////        mockMvc.perform(post("/auth/login")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(objectMapper.writeValueAsString(request)))
////                .andExpect(status().isOk())
////                .andExpect(content().string("JWT_TOKEN"));
////
////        verify(authService, times(1)).login(any(LoginRequest.class));
////    }
//
////    @Test
////    void testLoginInvalidCredentials() throws Exception {
////        LoginRequest request = new LoginRequest("user@example.com", "wrong_password");
////        when(authService.login(any(LoginRequest.class))).thenThrow(new IllegalArgumentException("Invalid credentials"));
////
////        mockMvc.perform(post("/auth/login")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(objectMapper.writeValueAsString(request)))
////                .andExpect(status().isUnauthorized())
////                .andExpect(jsonPath("$.message").value("Invalid credentials"));
////
////        verify(authService, times(1)).login(any(LoginRequest.class));
////    }
//}
