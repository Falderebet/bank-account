package com.bankdata.assignment.bankaccount.controllers;

import com.bankdata.assignment.bankaccount.exceptions.AccountNotFoundException;
import com.bankdata.assignment.bankaccount.exceptions.UsernameExistsException;
import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.models.User;
import com.bankdata.assignment.bankaccount.preloading.LoadDatabase;
import com.bankdata.assignment.bankaccount.repositories.AccountRepository;
import com.bankdata.assignment.bankaccount.repositories.UserRepository;
import com.bankdata.assignment.bankaccount.services.AccountService;
import org.aspectj.bridge.Message;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountService accountService;

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    AccountController(AccountRepository accountRepository, UserRepository userRepository,
                      AccountService accountService) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountService = accountService;
    }


    @PostMapping("/create")
    Account newAccount(@RequestBody Account newAccount) {

        // TODO: Dangerous to both save user and account - needs refactoring so there is a clear difference
        //  between user and account.
        //  Furthermore refactoring business logic into accountservice would probably be better.

        String username = newAccount.getUser().getUsername();
        if (userRepository.existsByUsername(username)) {
            UsernameExistsException exception = new UsernameExistsException(username);
            log.warn(exception.toString());
            throw exception;
        }

        Account account = accountService.createAccount(newAccount);
        User user = account.getUser();

        log.info("Saving user... " + userRepository.save(user));
        log.info("Saving account... " + newAccount);

        return accountRepository.save(account);
    }

    @GetMapping("/list")
    List<Account> allAccounts() {
        return accountRepository.findAll();
    }

}
