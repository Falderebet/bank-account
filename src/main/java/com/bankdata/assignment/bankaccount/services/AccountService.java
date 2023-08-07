package com.bankdata.assignment.bankaccount.services;

import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.models.User;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@Service
public class AccountService {

    // Creates and saves the account to the database.
    public Account createAccount(Account account) {
        Random random = new Random();

        SecureRandom secureRandom = new SecureRandom();

        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        account.setAccountNumber(random.nextInt(2147483647));

        User user = account.getUser();
        String hashedPassword = createHashedPassword(user.getPasswordHash(), salt);

        user.setSalt(Base64.getEncoder().encodeToString(salt));
        user.setPasswordHash(hashedPassword);

        return account;
    }

    // Creates a hashed password from a string and a byte array.
    private String createHashedPassword(String password, byte[] salt) {

        MessageDigest md;
        byte[] hashedPassword;

        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            hashedPassword = md.digest(Base64.getDecoder().decode(password));
        } catch (NoSuchAlgorithmException ex) {
            return ex.toString();
        }
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}
