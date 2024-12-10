package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
//        System.err.println("Ошибка аутентификации: " + e.getMessage());
        return new ResponseEntity<>("Неверные учетные данные", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<String> handleException(HttpException e) {
//        System.err.println(e.getMessage());
        return ResponseEntity.status(e.status).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
//        System.err.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + e.getMessage());
    }

    public static class HttpException extends RuntimeException {
        private final HttpStatus status;

        public HttpException(HttpStatus errorCode, String message) {
            super(message);
            this.status = errorCode;
        }
    }
}