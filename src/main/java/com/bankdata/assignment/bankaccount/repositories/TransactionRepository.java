package com.bankdata.assignment.bankaccount.repositories;

import com.bankdata.assignment.bankaccount.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
