package com.bankdata.assignment.bankaccount.controllers;

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
    void transferMoney(@RequestBody Account sourceAccount, @RequestBody Account destinationAccount, @RequestBody double amount) {
        double sourceBalance = sourceAccount.getBalance();
        double destinationBalance = destinationAccount.getBalance();

        sourceAccount.setBalance(sourceBalance - amount);
        destinationAccount.setBalance(destinationBalance + amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setSourceAccount(sourceAccount);

        transactionRepository.save(transaction);
    }

}
