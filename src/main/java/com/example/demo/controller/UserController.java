package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/")
@Tag(name = "User API", description = "API для управления пользователями и получения информации")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Получить информацию о пользователе",
            description = "Возвращает данные текущего пользователя на основе токена авторизации",
            parameters = {
                    @Parameter(
                            name = "Authorization",
                            description = "Токен авторизации",
                            required = true,
                            in = ParameterIn.HEADER,
                            schema = @Schema(type = "string", example = "Bearer exampleToken")
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация успешно получена"),
                    @ApiResponse(responseCode = "400", description = "Некорректный запрос"),
                    @ApiResponse(responseCode = "401", description = "Неавторизован")
            }
    )
    @GetMapping("/info")
    public ResponseEntity<?> getInfo(@RequestHeader("Authorization") String authorizationHeader) {
        UserDto user = userService.getInfo(authorizationHeader).toDto();
        ResponseEntity<String> response = new ResponseEntity<>(user.toString(), HttpStatus.OK);
        System.err.println("getInfo response: " + response);
        return response;
    }
}
