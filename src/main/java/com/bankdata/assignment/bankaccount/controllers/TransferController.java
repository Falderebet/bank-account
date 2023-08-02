package com.bankdata.assignment.bankaccount.controllers;

import com.bankdata.assignment.bankaccount.dtos.TransferRequest;
import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.models.Transaction;
import com.bankdata.assignment.bankaccount.repositories.AccountRepository;
import com.bankdata.assignment.bankaccount.repositories.TransactionRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransferController(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @PutMapping("/transfer")
    Transaction transferMoney(@RequestBody TransferRequest transferRequest) {

        Account sourceAccount = accountRepository.findByAccountNumber(transferRequest.getSourceAccountNumber());
        Account destinationAccount = accountRepository.findByAccountNumber(transferRequest.getDestinationAccountNumber());

        double sourceBalance = sourceAccount.getBalance();
        double destinationBalance = destinationAccount.getBalance();


        // TODO: error in transaction only show the destination account balance to be the amount.
        sourceAccount.setBalance(sourceBalance - transferRequest.getAmount());
        destinationAccount.setBalance(destinationBalance + transferRequest.getAmount());

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transaction transaction = new Transaction();
        transaction.setAmount(transferRequest.getAmount());
        transaction.setDestinationAccount(destinationAccount);
        transaction.setSourceAccount(sourceAccount);

        return transactionRepository.save(transaction);
    }

}
