package com.gdgBlog.gdgBlog.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse{
    private ErrorType type;

    private String code;

    private String message;

    private String errorStack;

    private LocalDateTime timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(ErrorType type, String code, String message) {
        this.type = type;
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(ErrorType type, String code, String message, Throwable e) {
        this.type = type;
        this.code = code;
        this.message = message;
        this.errorStack = e.getMessage();
        this.timestamp = LocalDateTime.now();
    }

}
