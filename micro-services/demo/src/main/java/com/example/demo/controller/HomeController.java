package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.bind.annotation.RequestBody;  


@RestController 
public class HomeController {
  @GetMapping("/")
  public String home() {
    return "Hello wonderful Spring Boot!\n\n"; 
  }

  @GetMapping("/other-endpoint")
  public String homeOther() {
    return "Hello wonderful from OTHER-ENDPOINT!\n\n"; 
  }

  @PostMapping(path="/say-hi-back", consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
    public String say_hi_back(@RequestParam(value = "data",
        defaultValue = "Enjoy your day!") String data) {
  return "Echo: " + data;
  }

  @PostMapping(path="/email-address-valid", consumes="application/json")
  @ResponseStatus(HttpStatus.OK)
  public String isEmailValid(@RequestParam(value = "email") String email) {
        String result;
        // More comprehensive email validation using regex
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if(email != null && email.matches(emailRegex)) {
            result = "Valid Email Address\n";
        } else {
            result = "Invalid Email Address\n";
        }
        return result;
    }

    @PostMapping(path="/check-password-strength", consumes="application/json")
    @ResponseStatus(HttpStatus.OK)
    public String checkPasswordStrength(@RequestParam(value = "password") String password) {
        int score = 0;
        String feedback = "";

        // Length check
        if (password.length() >= 8) {
            score += 1;
        }

        // Check for numbers
        if (password.matches(".*\\d.*")) {
            score += 1;
        }

        // Check for lowercase letters
        if (password.matches(".*[a-z].*")) {
            score += 1;
        }

        // Check for uppercase letters
        if (password.matches(".*[A-Z].*")) {
            score += 1;
        }

        // Check for special characters
            // Special characters are required for Very Strong only
            boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
            if (hasSpecial && score >= 4) {
            score += 1;
        }

        // Determine password strength based on score
        switch (score) {
            case 0:
            case 1:
                feedback = "Very Weak\n";
                break;
            case 2:
                feedback = "Weak\n";
                break;
            case 3:
                feedback = "Medium\n";
                break;
            case 4:
                feedback = "Strong\n";
                break;
            case 5:
                feedback = "Very Strong\n";
                break;
        }

        return feedback;
    }
}
