package com.example.service_b.config;


import com.example.service_b.exception.InvalidEmailException;
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
        System.out.println("СЛАВА РОССИИ в составе Беларуси");
        return ResponseEntity.status(exc.getStatus())
                .body(ErrorMessage.builder()
                        .status(HttpStatus.resolve(exc.getStatus().value()))
                        .message(exc.getMessage())
                        .cause(String.valueOf(exc.getCause())) // Используем поле cause из исключения
                        .build());
    }

    @Getter
    @Builder
    @Jacksonized
    public static class ErrorMessage {

        @Builder.Default
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        private LocalDateTime date = LocalDateTime.now();
        private HttpStatus status;
        private String message;
        private String cause; // Поле для хранения причины (имя класса исключения)
    }
}