package com.example.demo.models;


import com.example.demo.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true)
    private String token;

    public UserDto toDto(){
        UserDto dto = new UserDto();
        dto.setEmail(email);
        dto.setRole(role);
        return dto;
    }
}