package com.bankdata.assignment.bankaccount.repositories;

import com.bankdata.assignment.bankaccount.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
