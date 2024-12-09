package com.example.demo.models.task;


import com.example.demo.models.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Модель задачи")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор задачи", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank
    @Schema(description = "Заголовок задачи", example = "Исправить баг", required = true)
    private String title;

    @Schema(description = "Описание задачи", example = "Исправить баг с отображением на главной странице")
    private String description;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Статус задачи", example = "IN_PROGRESS")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Приоритет задачи", example = "HIGH")
    private Priority priority;

    @ManyToOne
    @Schema(description = "Автор задачи (создатель)")
    private User author;

    @ManyToOne
    @Schema(description = "Исполнитель задачи")
    private User assignee;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Комментарии к задаче")
    private List<Comment> comments;

    public void copy(Task newTask) {
        if (newTask == null) {
            throw new IllegalArgumentException("Объект для копирования не может быть null");
        }

        this.title = newTask.title;
        this.description = newTask.description;
        this.status = newTask.status;
        this.priority = newTask.priority;
        this.author = newTask.author;
        this.assignee = newTask.assignee;
        this.comments = newTask.comments != null ? new ArrayList<>(newTask.comments) : null;
    }

}
