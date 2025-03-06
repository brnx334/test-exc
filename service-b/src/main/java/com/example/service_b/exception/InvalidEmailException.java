package com.example.service_b.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidEmailException extends RuntimeException {
    private final HttpStatus status;
    public InvalidEmailException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}