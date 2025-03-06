package com.example.service_c.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidEmailException extends RuntimeException {
    private final HttpStatus status;
    private final String originalCause;
    public InvalidEmailException(String message, HttpStatus status, String originalCause) {
        super(message);
        this.status = status;
        this.originalCause = originalCause; // Сохраняем причину как строку
    }
}
