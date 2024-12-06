package com.example.demo.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String content;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User author;
}