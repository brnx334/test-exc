package com.example.service_b.controller;

import com.example.service_b.exception.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/service-b")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/validate-email")
    public ResponseEntity<String> validateEmail(@RequestParam String email) throws InvalidEmailException {
        return emailService.validateEmail(email);
    }
}