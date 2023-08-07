package com.bankdata.assignment.bankaccount.exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(double balance, double amount) {
        super("Insufficient funds, balance=" + balance + " transferAmount=" + amount);
    }
}
