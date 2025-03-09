package com.example.service_b.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ParentException extends RuntimeException {
    protected final String originalCause;
    protected final LocalDateTime date;
    protected final HttpStatus httpStatus;

    public ParentException(
            final HttpStatus httpStatus,
            final String message,
            final String originalCause,
            final LocalDateTime date) {
        super(message);
        this.httpStatus = httpStatus;
        this.originalCause = originalCause;
        this.date = date;
    }
}
