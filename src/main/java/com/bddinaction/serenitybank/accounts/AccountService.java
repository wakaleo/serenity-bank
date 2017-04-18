package com.bddinaction.serenitybank.accounts;

import com.bddinaction.serenitybank.model.AccountType;
import com.bddinaction.serenitybank.model.BankAccount;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.bddinaction.serenitybank.accounts.AccountOrdering.firstOf;
import static com.bddinaction.serenitybank.accounts.AccountOrdering.lastOf;

public class AccountService {

    Map<String, BankAccount> accounts = new ConcurrentHashMap<>();

    public String createNewAccount(String accountNumber, AccountType type, BigDecimal initialDeposit) {
        BankAccount newAccount = new BankAccount(accountNumber,type);
        newAccount.deposit(initialDeposit);
        accounts.put(accountNumber, newAccount);
        return accountNumber;
    }

    public void transferFunds(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {

        BankAccount fromAccount = accounts.get(fromAccountNumber);
        BankAccount toAccount = accounts.get(toAccountNumber);

        synchronized (firstOf(fromAccount, toAccount)) {
            synchronized (lastOf(fromAccount, toAccount)) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
            }
        }
    }

    public BigDecimal getBalance(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        return account.getBalance();
    }

    public void makeDeposit(String accountNumber, BigDecimal amount) {
        BankAccount account = accounts.get(accountNumber);
        account.deposit(amount);
    }
}
