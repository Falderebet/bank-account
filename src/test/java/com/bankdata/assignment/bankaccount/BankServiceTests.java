package com.bankdata.assignment.bankaccount;

import com.bankdata.assignment.bankaccount.dtos.TransferRequest;
import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.models.Transaction;
import com.bankdata.assignment.bankaccount.models.User;
import com.bankdata.assignment.bankaccount.repositories.AccountRepository;
import com.bankdata.assignment.bankaccount.repositories.UserRepository;
import com.bankdata.assignment.bankaccount.services.AuthenticationService;
import com.bankdata.assignment.bankaccount.services.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankServiceTests {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final AccountRepository accountRepository = mock(AccountRepository.class);

    private AuthenticationService authenticationService;
    private TransferService transferService;

    private User user;
    private Account account;
    private Account account1;


    @BeforeEach
    public void setUp() {
        // Not an optimal way, however it showcases the use of a mock repository and test setup.
        populateAccounts();
        mockRepositories();
        authenticationService = new AuthenticationService(userRepository);
        transferService = new TransferService(authenticationService, accountRepository);
    }

    @Test
    public void transferMoneyTest() {
        Transaction transaction = transferService.runTransaction(account, account1, 10);
        assert(transaction != null);
        assert(transaction.getAmount() == 10);
    }

    private TransferRequest generateTransferRequest(double amount, int destinationNumber, int sourceNumber,
                                                    String password, User sourceUser) {
        TransferRequest tr = new TransferRequest();

        tr.setAmount(amount);
        tr.setDestinationAccountNumber(destinationNumber);
        tr.setSourceAccountNumber(sourceNumber);
        tr.setSourcePassword(password);
        tr.setSourceUser(sourceUser);

        return tr;
    }

    private User generateUser(String username, String passwordHash, String salt) {
        User user = new User();
        user.setUsername(username);
        user.setSalt(salt);
        user.setPasswordHash(passwordHash);
        return user;
    }

    private Account generateAccount(int accountNumber, User user) {
        Account account = new Account();
        account.setBalance(100);
        account.setAccountNumber(accountNumber);
        account.setUser(user);
        return account;
    }

    private void mockRepositories() {
        List<Account> accounts = new ArrayList<>();

        accounts.add(account);
        accounts.add(account1);

        when(accountRepository.findAll()).thenReturn(accounts);
        when(userRepository.findByUsername("jens123")).thenReturn(user);
        when(accountRepository.findByAccountNumber(99)).thenReturn(account);
        when(accountRepository.findByAccountNumber(88)).thenReturn(account1);
    }

    private void populateAccounts() {
        user = generateUser("jens123", "h8j9", "jr");
        account = generateAccount(99, user);
        User user1 = generateUser("peter123", "h8j10", "jt");
        account1 = generateAccount(88, user1);
    }

}
