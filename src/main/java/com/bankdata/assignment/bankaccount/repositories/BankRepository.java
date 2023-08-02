package com.bankdata.assignment.bankaccount.repositories;

import com.bankdata.assignment.bankaccount.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
