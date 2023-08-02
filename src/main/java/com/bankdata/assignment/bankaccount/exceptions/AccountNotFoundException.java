package com.bankdata.assignment.bankaccount.exceptions;

import com.bankdata.assignment.bankaccount.controllers.AccountController;
import com.bankdata.assignment.bankaccount.models.Account;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long id) {
        super("Could not find account " + id);
    }
}
