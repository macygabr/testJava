package com.example.demo.controller;

import com.example.demo.models.task.Priority;
import com.example.demo.models.task.Status;
import com.example.demo.models.task.Task;
import com.example.demo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/task/")
@RequiredArgsConstructor
@Tag(name = "Задачи")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    @Operation(
            summary = "Создание новой задачи",
            description = "Позволяет создать новую задачу, указав заголовок, описание, статус, приоритет и назначенного исполнителя.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Задача успешно создана",
                            content = @Content(schema = @Schema(implementation = Task.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Некорректные входные данные")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    public Task create(@RequestParam @NotBlank String title,
                       @RequestParam @NotBlank String description,
                       @RequestParam @Valid Status status,
                       @RequestParam @Valid Priority priority,
                       @RequestParam @NotBlank String assignee) {
        return taskService.createTask(title, description, status, priority, assignee);
    }



    @PatchMapping("/change/{id}")
    @Operation(
            summary = "Редактирование задачи",
            description = "Позволяет изменить параметры существующей задачи."
    )
    @PreAuthorize("hasRole('ADMIN')")
    public Task change(@PathVariable Long id,
                       @RequestParam @NotBlank String title,
                       @RequestParam @NotBlank String description,
                       @RequestParam @Valid Status status,
                       @RequestParam @Valid Priority priority,
                       @RequestParam @NotBlank String assignee) {
        return taskService.change(id, title, description, status, priority, assignee);
    }


    @GetMapping("/get")
    @Operation(
            summary = "Просмотр задач",
            description = "Получение списка всех задач."
    )
    @PreAuthorize("hasRole('ADMIN')")
    public List<Task> get() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Удаление задачи",
            description = "Удаляет задачу по указанному идентификатору.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача успешно удалена"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Задача удалена";
    }

    @PatchMapping("/change_status/{id}")
    @Operation(
            summary = "Изменение статуса задачи",
            description = "Изменяет статус задачи на указанный.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Статус задачи успешно изменён"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    public String changeStatus(@PathVariable Long id, @RequestParam @Valid Status status) {
        taskService.changeStatus(id, status);
        return "Статус изменён";
    }

    @PatchMapping("/change_priority/{id}")
    @Operation(
            summary = "Изменение приоритета задачи",
            description = "Обновляет приоритет указанной задачи."
    )
    @PreAuthorize("hasRole('ADMIN')")
    public String changePriority(@PathVariable Long id, @RequestParam @Valid Priority priority) {
        taskService.changePriority(id, priority);
        return "Приоритет изменён";
    }

    @PatchMapping("/set_assignee/{id}")
    @Operation(
            summary = "Назначение исполнителя",
            description = "Устанавливает исполнителя для указанной задачи."
    )
    @PreAuthorize("hasRole('ADMIN')")
    public String setAssignee(@PathVariable Long id, @RequestParam @NotBlank String assignee) {
        taskService.setAssignee(id, assignee);
        return "Исполнитель назначен";
    }

    @PatchMapping("/comment/{id}")
    @Operation(
            summary = "Добавление комментария",
            description = "Позволяет добавить комментарий к задаче."
    )
    @PreAuthorize("hasRole('ADMIN')")
    public String comment(@PathVariable Long id, @RequestParam @NotBlank String comment) {
        taskService.addComment(id, comment);
        return "Комментарий добавлен";
    }
}
