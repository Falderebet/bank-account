package com.bankdata.assignment.bankaccount.exceptions;

public class AccountNumberNotFoundException extends RuntimeException {
    public AccountNumberNotFoundException(int sourceAccountNumber) {
        super("Account with account number not found: " + sourceAccountNumber);
    }
}
