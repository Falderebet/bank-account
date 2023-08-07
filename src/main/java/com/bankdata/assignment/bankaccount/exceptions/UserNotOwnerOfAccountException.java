package com.bankdata.assignment.bankaccount.exceptions;

public class UserNotOwnerOfAccountException extends RuntimeException {
    public UserNotOwnerOfAccountException(int accountNumber) {
        super("User is not owner of account: " + accountNumber);
    }
}
