package com.bankdata.assignment.bankaccount.exceptions;

public class UsernameOrPasswordWrongException extends RuntimeException {
    public UsernameOrPasswordWrongException(String username, String passwordHash) {
        super("Wrong username or password (" + username + ", " + passwordHash + ")");
    }
}
