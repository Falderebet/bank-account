package com.bankdata.assignment.bankaccount.exceptions;

public class DestinationAccountDoesNotExistException extends RuntimeException {
    public DestinationAccountDoesNotExistException(int destinationAccountNumber) {
        super("The destination account does not exists: " + destinationAccountNumber);
    }
}
