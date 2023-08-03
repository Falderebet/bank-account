package com.bankdata.assignment.bankaccount.dtos;

import com.bankdata.assignment.bankaccount.models.User;

public class TransferRequest {
    private int sourceAccountNumber;
    private int destinationAccountNumber;
    private User sourceUser;
    private double amount;

    public User getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(User sourceUser) {
        this.sourceUser = sourceUser;
    }

    public int getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(int sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public int getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(int destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
