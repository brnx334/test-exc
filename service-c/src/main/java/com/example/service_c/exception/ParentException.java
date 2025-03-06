package com.example.service_c.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ParentException extends Exception {

    protected final HttpStatus status;

    public ParentException(final HttpStatus status, final String message) {
        super(message);
        this.status = status;
    }

}
