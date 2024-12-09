//package com.example.demo.controller;
//
//import com.example.demo.dto.UserDto;
//import com.example.demo.service.UserService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.enums.ParameterIn;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/api/user/")
//@Tag(name = "User API", description = "API для управления пользователями и получения информации")
//public class UserController {
//    private final UserService userService;
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    @Operation(summary = "Доступен только авторизованным пользователям")
//    public String example() {
//        return "Hello, world!";
//    }
//
//    @GetMapping("/admin")
//    @Operation(summary = "Доступен только авторизованным пользователям с ролью ADMIN")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String exampleAdmin() {
//        return "Hello, admin!";
//    }
//
//    @GetMapping("/get-admin")
//    @Operation(summary = "Получить роль ADMIN (для демонстрации)")
//    public void getAdmin() {
//        userService.getAdmin();
//    }
//}
