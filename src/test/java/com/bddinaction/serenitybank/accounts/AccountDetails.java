package com.bddinaction.serenitybank.accounts;

import com.bddinaction.serenitybank.model.AccountType;

import java.math.BigDecimal;

public class AccountDetails {
    private final String number;
    private final AccountType type;
    private final BigDecimal balance;

    public AccountDetails(String number, AccountType type, BigDecimal balance) {
        this.number = number;
        this.type = type;
        this.balance = balance;
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
}
