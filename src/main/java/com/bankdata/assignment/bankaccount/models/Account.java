package com.bankdata.assignment.bankaccount.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToMany()
    private List<User> users;

    private double balance;

    public Account(Bank bank, List<User> users, double balance) {
        this.bank = bank;
        this.users = users;
        this.balance = balance;
    }

    public Account() {}

    public Account(Bank bank, long balance) {
        this.bank = bank;
        this.balance = balance;
    }

    public Account(Long balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + this.id + ", balance=" + this.balance + ", bank=" + this.bank + "}";
    }

}
