package com.example.service_c.controller;

import com.example.service_c.exception.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-c")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/validate-email")
    public ResponseEntity<String> validateEmail(@RequestParam String email) throws InvalidEmailException {
        String result = emailService.validateEmail(email);
        return ResponseEntity.ok(result);
    }
}