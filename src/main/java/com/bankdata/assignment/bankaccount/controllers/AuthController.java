package com.bankdata.assignment.bankaccount.controllers;

import com.bankdata.assignment.bankaccount.dtos.LoginRequest;
import com.bankdata.assignment.bankaccount.models.User;
import com.bankdata.assignment.bankaccount.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    // Endpoint for logging in (used by frontend) not complete.
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {

        MessageDigest md;
        byte[] hashedPassword;

        User user = userRepository.findByUsername(loginRequest.getUsername());

        String salt = user.getSalt();

        try {
            md = MessageDigest.getInstance("SHA-512");

        } catch (NoSuchAlgorithmException ex) {
            // TODO: needs better handling.
            System.out.println(ex.toString());
            return "404";
        }

        md.update(Base64.getDecoder().decode(salt));
        hashedPassword = md.digest(Base64.getDecoder().decode(loginRequest.getPassword()));

        if( user == null || !user.getPasswordHash().equals(Base64.getEncoder().encodeToString(hashedPassword))) {
            // TODO: needs better handling.
            return "400";
        }

        return "200";

    }
}
