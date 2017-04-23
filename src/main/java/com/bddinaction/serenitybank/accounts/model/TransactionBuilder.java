package com.bddinaction.serenitybank.accounts.model;

import org.joda.time.LocalDate;

import java.math.BigDecimal;

public class TransactionBuilder {
    private BigDecimal amount;
    private String description;
    private LocalDate date;

    public TransactionBuilder(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public TransactionBuilder on(LocalDate date) {
        this.date = date;
        return this;
    }

    public Transaction andNewBalance(BigDecimal balance) {
        return new Transaction(amount, description, date, balance, 0);
    }
}
