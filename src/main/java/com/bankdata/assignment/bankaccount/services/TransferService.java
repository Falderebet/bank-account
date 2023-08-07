package com.bankdata.assignment.bankaccount.services;

import com.bankdata.assignment.bankaccount.dtos.TransferRequest;
import com.bankdata.assignment.bankaccount.exceptions.AccountNumberNotFoundException;
import com.bankdata.assignment.bankaccount.exceptions.DestinationAccountDoesNotExistException;
import com.bankdata.assignment.bankaccount.exceptions.InsufficientFundsException;
import com.bankdata.assignment.bankaccount.exceptions.UserNotFoundException;
import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.models.Transaction;
import com.bankdata.assignment.bankaccount.models.User;
import com.bankdata.assignment.bankaccount.repositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private final AuthenticationService authenticationService;
    private final AccountRepository accountRepository;

    public TransferService(AuthenticationService authenticationService, AccountRepository accountRepository) {
        this.authenticationService = authenticationService;
        this.accountRepository = accountRepository;
    }

    public String getHashedPassword(TransferRequest transferRequest) {
        User sourceUser = transferRequest.getSourceUser();
        String hashedPassword =
                authenticationService.convertPasswordToHashedPassword(sourceUser.getUsername(),
                        sourceUser.getPasswordHash());
        return hashedPassword;
    }

    public Transaction runTransaction(Account sourceAccount, Account destinationAccount, double amount) {

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

        return transaction;
    }

    public Transaction completeTransaction(TransferRequest transferRequest) {

        String hashedPassword = getHashedPassword(transferRequest);

        // Checks whether the hashedPassword was successfully "gathered".
        if (hashedPassword == null) {
            throw new UserNotFoundException(transferRequest.getSourceUser().getUsername());
        }

        // Setup for checking of user is owner of account.
        Account sourceAccount = accountRepository.findByAccountNumber(transferRequest.getSourceAccountNumber());

        if (sourceAccount == null) {
            throw new AccountNumberNotFoundException(transferRequest.getSourceAccountNumber());
        }

        // Checking whether the given user has right to transfer money from the source account.
        authenticationService.accountNumberUserRights(sourceAccount, transferRequest.getSourceUser(), hashedPassword);

        // Checking whether the destination account exists.
        Account destinationAccount = accountRepository.findByAccountNumber(transferRequest.getDestinationAccountNumber());

        if (destinationAccount == null) {
            throw new DestinationAccountDoesNotExistException(transferRequest.getDestinationAccountNumber());
        }

        // Check whether the source balance has enough funds.
        if (sourceAccount.getBalance() < transferRequest.getAmount()) {
            throw new InsufficientFundsException(sourceAccount.getBalance(), transferRequest.getAmount());
        }

        if (transferRequest.getAmount() < 0) {
            // TODO: create a amount needs to be positive exception or handler.
            throw new RuntimeException("Amount needs to be a positive number");
        }

        Transaction transaction = runTransaction(sourceAccount, destinationAccount,
                transferRequest.getAmount());

        return transaction;
    }
}
