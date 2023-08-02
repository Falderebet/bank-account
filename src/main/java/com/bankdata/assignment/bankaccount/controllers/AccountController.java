package com.bankdata.assignment.bankaccount.controllers;

import com.bankdata.assignment.bankaccount.exceptions.AccountNotFoundException;
import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.models.User;
import com.bankdata.assignment.bankaccount.preloading.LoadDatabase;
import com.bankdata.assignment.bankaccount.repositories.AccountRepository;
import com.bankdata.assignment.bankaccount.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    AccountController(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    Account newAccount(@RequestBody Account newAccount) {

        //TODO: Needs cleaning as well as dangerous to both save a user and save account?
        Random random = new Random();

        newAccount.setAccountNumber(random.nextInt(2147483647));
        User user = newAccount.getUser();

        log.info("Saving user... " + userRepository.save(user));
        log.info("Saving account... " + newAccount);
        return accountRepository.save(newAccount);
    }

    @GetMapping("/list")
    List<Account> allAccounts() {
        return accountRepository.findAll();
    }
}
