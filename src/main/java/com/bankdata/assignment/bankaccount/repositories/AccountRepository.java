package com.bankdata.assignment.bankaccount.repositories;

import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNumber(int accountNumber);
}
