package com.example.service_b.controller;

import com.example.service_b.config.GlobalExceptionHandler;
import com.example.service_b.exception.InvalidEmailException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.io.InputStream;

@Service
public class EmailService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public EmailService(RestClient restClient, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<String> validateEmail(String email) throws InvalidEmailException {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/service-c/validate-email")
                        .queryParam("email", email)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        (request, response) -> {

                            InputStream responseBody = response.getBody();

                            String responseBodyString = new String(responseBody.readAllBytes());

                            GlobalExceptionHandler.ErrorMessage errorResponse =
                                    objectMapper.readValue(
                                            responseBodyString,
                                            GlobalExceptionHandler.ErrorMessage.class
                                    );

                            throw new InvalidEmailException(
                                    errorResponse.getMessage(),
                                    errorResponse.getOriginalCause(),
                                    errorResponse.getDate()
                            );
                        })
                .toEntity(String.class);
    }
}
