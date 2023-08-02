package com.bankdata.assignment.bankaccount.repositories;

import com.bankdata.assignment.bankaccount.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
