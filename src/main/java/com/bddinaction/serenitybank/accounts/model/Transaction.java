package com.bddinaction.serenitybank.accounts.model;

import com.google.common.base.Objects;
import org.joda.time.LocalDate;

import java.math.BigDecimal;

public class Transaction {
    private final BigDecimal amount;
    private final String description;
    private final LocalDate date;
    private final BigDecimal balance;
    private final Integer number;

    public Transaction(BigDecimal amount, String description, LocalDate date, BigDecimal balance, Integer number) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.balance = balance;
        this.number = number;
    }

    public Transaction withNumber(Integer number) {
        return new Transaction(amount, description, date, balance, number);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equal(amount, that.amount) &&
                Objects.equal(description, that.description) &&
                Objects.equal(date, that.date) &&
                Objects.equal(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount, description, date, balance);
    }

    @Override
    public String toString() {
        return amount + " | " + description + " | " + date + " || " + balance;
    }

    public static TransactionBuilder of(BigDecimal amount) {
        return new TransactionBuilder(amount);
    }
}
