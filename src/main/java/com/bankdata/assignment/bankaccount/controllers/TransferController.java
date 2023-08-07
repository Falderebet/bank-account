package com.bankdata.assignment.bankaccount.controllers;

import com.bankdata.assignment.bankaccount.dtos.TransferRequest;
import com.bankdata.assignment.bankaccount.exceptions.*;
import com.bankdata.assignment.bankaccount.exceptions.DestinationAccountDoesNotExistException;
import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.models.Transaction;
import com.bankdata.assignment.bankaccount.models.User;
import com.bankdata.assignment.bankaccount.repositories.AccountRepository;
import com.bankdata.assignment.bankaccount.repositories.TransactionRepository;
import com.bankdata.assignment.bankaccount.repositories.UserRepository;
import com.bankdata.assignment.bankaccount.services.AuthenticationService;
import com.bankdata.assignment.bankaccount.services.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private static final Logger log = LoggerFactory.getLogger(TransferController.class);
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransferService transferService;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public TransferController(AccountRepository accountRepository, TransactionRepository transactionRepository,
                              TransferService transferService, UserRepository userRepository,
                              AuthenticationService authenticationService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transferService = transferService;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @PutMapping("/transfer")
    Transaction transferMoney(@RequestBody TransferRequest transferRequest) {
        Transaction transaction = transferService.completeTransaction(transferRequest);

        // TODO: Probably a good idea to remove some information like the hashed password and salt before sending back
        //  to client.
        return transactionRepository.save(transaction);
    }

}
