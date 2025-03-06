package com.example.service_a.config;


import com.example.service_a.exception.InvalidEmailException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorMessage> handleInvalidEmailException(final InvalidEmailException exc) {
        return ResponseEntity.status(exc.getStatus())
                .body(ErrorMessage.builder()
                        .status(HttpStatus.resolve(exc.getStatus().value()))
                        .message(exc.getMessage())
                        .cause(exc.getClass())
                        .build());
    }

    @Getter
    @Builder
    @Jacksonized
    public static class ErrorMessage {

        @Builder.Default
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private LocalDateTime date = LocalDateTime.now();
        private HttpStatus status;
        private String message;
        private Class<? extends Throwable> cause;
    }
}