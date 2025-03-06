package com.example.service_a.controller;

import com.example.service_a.exception.InvalidEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

@Service
public class EmailService {


    private final RestClient restClient;

    public EmailService(RestClient restClient) {
        this.restClient = restClient;
    }
    public ResponseEntity<String> validateEmail(String email) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/service-b/validate-email")
                        .queryParam("email", email)
                        .build())
                .retrieve()
                .onStatus(status -> status == HttpStatus.CONFLICT,
                        (request, response) -> {
                            throw new InvalidEmailException("Invalid email: " + email, HttpStatus.CONFLICT);
                        })
                .toEntity(String.class);
    }
}