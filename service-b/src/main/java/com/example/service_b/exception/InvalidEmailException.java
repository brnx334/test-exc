package com.example.service_b.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends ParentException {

    public InvalidEmailException(String message, String cause) {
        super(HttpStatus.BAD_REQUEST, message, cause, LocalDateTime.now());
    }

    public InvalidEmailException(String message, String cause, LocalDateTime date) {
        super(HttpStatus.BAD_REQUEST, message, cause, date);
    }
}