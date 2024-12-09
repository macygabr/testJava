package com.example.demo.models.task;


import com.example.demo.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Schema(description = "Комментарий к задаче")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор комментария", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Содержимое комментария", example = "Добавить тесты", required = true)
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @Schema(description = "Связанная задача")
    @JsonIgnore
    private Task task;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @Schema(description = "Автор комментария")
    private User author;
}