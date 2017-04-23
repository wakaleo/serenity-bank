package com.bddinaction.serenitybank.model;

import com.bddinaction.serenitybank.accounts.model.Transaction;
import com.bddinaction.serenitybank.accounts.model.TransactionHistory;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.List;

public class BankAccount {
    private final String number;
    private final AccountType type;

    private BigDecimal balance;
    private TransactionHistory transactionHistory = new TransactionHistory();

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
        deposit(amount, LocalDate.now());
    }

    public void deposit(BigDecimal amount, LocalDate date) {
        synchronized (lock) {
            balance = balance.add(amount);
            transactionHistory.add(Transaction.of(amount).on(date).withDescription("deposit").andNewBalance(balance));
        }
    }

    public void withdraw(BigDecimal amount) {
        withdraw(amount, LocalDate.now());
    }

    public void withdraw(BigDecimal amount, LocalDate date) {
        withdraw(amount, date, "withdrawal");
    }

    public void withdraw(BigDecimal amount, LocalDate date, String description) {
        synchronized (lock) {
            if (!isZero(amount)) {
                balance = balance.subtract(amount);
                transactionHistory.add(Transaction.of(amount.negate()).on(date).withDescription(description).andNewBalance(balance));
            }
        }
    }

    private boolean isZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) == 0;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory.getTransactions();
    }
}
