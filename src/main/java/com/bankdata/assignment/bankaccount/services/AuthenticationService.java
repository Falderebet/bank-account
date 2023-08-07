package com.bankdata.assignment.bankaccount.services;

import com.bankdata.assignment.bankaccount.controllers.AccountController;
import com.bankdata.assignment.bankaccount.exceptions.UserNotOwnerOfAccountException;
import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.models.User;
import com.bankdata.assignment.bankaccount.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class AuthenticationService {

    UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String convertPasswordToHashedPassword(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        String salt = user.getSalt();

        if (salt == null) {
            return null;
        }

        return hashPassword(salt, password);
    }

    private String hashPassword(String salt, String password) {
        MessageDigest md;
        byte[] hashedPassword;

        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(Base64.getDecoder().decode(salt));
            hashedPassword = md.digest(Base64.getDecoder().decode(password));

        } catch (NoSuchAlgorithmException ex) {
            // TODO: needs better handling.
            log.error(ex.getMessage());
            return null;
        }

        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    // Authenticated if a user can transfer money from an account.
    public void accountNumberUserRights(Account sourceAccount, User sourceUser, String hashedPassword) {

        boolean userNameMatching = sourceAccount.getUser().getUsername().equals(sourceUser.getUsername());
        boolean passwordHashMatching = sourceAccount.getUser().getPasswordHash().equals(hashedPassword);

        // Checks whether the user has access to the source account.
        if (!(userNameMatching && passwordHashMatching)) {
            UserNotOwnerOfAccountException exception =
                    new UserNotOwnerOfAccountException(sourceAccount.getAccountNumber());
            log.warn(exception.getMessage());
            throw exception;
        }
    }
}
