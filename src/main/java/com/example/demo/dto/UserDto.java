package com.example.demo.dto;

import com.example.demo.models.Role;
import lombok.Data;


@Data
public class UserDto {
    private String email;
    private String password;
    private Role role;
}