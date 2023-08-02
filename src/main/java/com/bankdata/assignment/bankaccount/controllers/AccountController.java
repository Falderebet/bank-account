package com.bankdata.assignment.bankaccount.controllers;

import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.repositories.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {


    private final AccountRepository accountRepository;

    AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping("/create")
    Account newAccount(@RequestBody Account newAccount) {
        return accountRepository.save(newAccount);
    }

    @GetMapping("/list")
    List<Account> allAccounts() {

        return accountRepository.findAll();
    }
}
