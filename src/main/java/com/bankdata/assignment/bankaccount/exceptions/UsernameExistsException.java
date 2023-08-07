package com.bankdata.assignment.bankaccount.exceptions;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String username) {
        super("User with username \"" + username + "\" already exists ");
    }
}
