package com.bddinaction.serenitybank.model;

import java.math.BigDecimal;

public class BankAccount {
    private final String number;
    private final AccountType type;
    private BigDecimal balance;

    private final Object lock = new Object();

    public BankAccount(String accountNumber, AccountType accountType) {
        this.number = accountNumber;
        this.type = accountType;
        this.balance = BigDecimal.ZERO;
    }

    public String getNumber() {
        return number;
    }

    public AccountType getType() {
        return type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        synchronized (lock) {
            balance = balance.add(amount);
        }
    }

    public void withdraw(BigDecimal amount) {
        synchronized (lock) {
            balance = balance.subtract(amount);
        }
    }
}
