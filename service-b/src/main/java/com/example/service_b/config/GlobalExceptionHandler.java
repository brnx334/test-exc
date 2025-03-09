package com.example.service_b.config;


import com.example.service_b.exception.ParentException;
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

    @ExceptionHandler(ParentException.class)
    public ResponseEntity<ErrorMessage> handleInvalidEmailException(final ParentException exc) {
        return ResponseEntity.status(exc.getHttpStatus())
                .body(ErrorMessage.builder()
                        .status(exc.getHttpStatus())
                        .message(exc.getMessage())
                        .originalCause(exc.getOriginalCause())
                        .date(exc.getDate())
                        .build()
                );
    }

    @Getter
    @Builder
    @Jacksonized
    public static class ErrorMessage {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime date;
        private HttpStatus status;
        private String message;
        private String originalCause;
    }
}