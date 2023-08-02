package com.bankdata.assignment.bankaccount.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "bank")
    private List<Account> accounts;

    public Bank() {};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
