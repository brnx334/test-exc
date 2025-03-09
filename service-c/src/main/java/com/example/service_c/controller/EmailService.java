package com.example.service_c.controller;

import com.example.service_c.exception.InvalidEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class EmailService {

    public String validateEmail(String email) throws InvalidEmailException {
        if (!isValidEmail(email)) {
            throw new InvalidEmailException("Invalid email: " + email, InvalidEmailException.class.getName());
        }
        return "Email is valid: " + email;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}
