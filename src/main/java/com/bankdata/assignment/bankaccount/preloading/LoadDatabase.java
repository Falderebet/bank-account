package com.bankdata.assignment.bankaccount.preloading;

import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.models.Bank;
import com.bankdata.assignment.bankaccount.repositories.AccountRepository;
import com.bankdata.assignment.bankaccount.repositories.BankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepository accountRepository, BankRepository bankRepository) {
        return args -> {
            log.info("Preloading " + accountRepository.save(new Account(100L)));
            log.info("Preloading " + accountRepository.save(new Account(120L)));
            log.info("Preloading " + accountRepository.save(new Account(160L)));
            log.info("Preloading " + accountRepository.save(new Account(110L)));
            log.info("Preloading " + accountRepository.save(new Account(190L)));
            log.info("Preloading " + bankRepository.save(new Bank()));
            log.info("Finding... " + accountRepository.findAll());
        };
    }
}
