package com.bankdata.assignment.bankaccount.preloading;

import com.bankdata.assignment.bankaccount.models.Account;
import com.bankdata.assignment.bankaccount.models.User;
import com.bankdata.assignment.bankaccount.repositories.AccountRepository;
import com.bankdata.assignment.bankaccount.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    @Bean
    @Transactional
    CommandLineRunner initDatabase(AccountRepository accountRepository, UserRepository userRepository) {
        User user = new User();
        user.setEmail("frederik@mail.com");
        user.setUsername("falderebet");
        user.setPasswordHash("gjerg3ijg341");
        userRepository.save(user);


        return args -> {
            //log.info("Preloading " + accountRepository.save(new Account(100L)));
            //log.info("Preloading " + accountRepository.save(new Account(120L)));
            //log.info("Preloading " + accountRepository.save(new Account(160L)));
            //log.info("Preloading " + accountRepository.save(new Account(110L)));

            log.info("Preloading " + accountRepository.save(new Account(user,190L)));
            log.info("Finding... " + accountRepository.findAll());
        };
    }
}
