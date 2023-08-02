package com.bankdata.assignment.bankaccount.models;

import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    // TODO: make it handle multiple users to one account.
    // @ManyToMany()
    // private List<User> users;

    @OneToOne
    private User user;

    // TODO: get "unique" tag working.
    // @Column(unique = true)
    private int accountNumber;

    private double balance;

    public Account(User user, double balance) {
        this.user = user;
        this.balance = balance;
    }

    public Account() {}

    public Account(long balance) {
        this.balance = balance;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long id) {
        this.accountId = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + this.accountId + ", balance=" + this.balance + ", users=" + this.user
                + ", accountnumber=" + this.accountNumber + "}";
    }

}
