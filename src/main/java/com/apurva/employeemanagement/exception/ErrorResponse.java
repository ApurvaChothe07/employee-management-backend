package com.apurva.employeemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private Map<String, String> errors;

    public ErrorResponse(
            LocalDateTime timestamp,
            int status,
            String message) {

        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(
            LocalDateTime now,
            int status,
            Map<String, String> errors,
            String message) {

        this.timestamp = now;
        this.status = status;
        this.errors = errors;
        this.message = message;
    }
}