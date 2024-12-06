package com.example.demo.models;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {

    private final HttpStatus status;

    public HttpException(HttpStatus errorCode, String message) {
        super(message);
        this.status = errorCode;
    }
}
