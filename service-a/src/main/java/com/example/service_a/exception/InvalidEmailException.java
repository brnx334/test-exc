package com.example.service_a.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidEmailException extends ParentException {

    public InvalidEmailException(String message, HttpStatus status) {
        super(HttpStatus.CONFLICT, message);
    }
}
