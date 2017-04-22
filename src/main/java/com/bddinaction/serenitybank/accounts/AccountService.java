package com.bddinaction.serenitybank.accounts;

import com.bddinaction.serenitybank.deposits.DepositFee;
import com.bddinaction.serenitybank.model.AccountType;
import com.bddinaction.serenitybank.model.BankAccount;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static com.bddinaction.serenitybank.accounts.AccountOrdering.firstOf;
import static com.bddinaction.serenitybank.accounts.AccountOrdering.lastOf;

public class AccountService {

    Map<String, BankAccount> accounts = new ConcurrentHashMap<>();

    AtomicInteger accountNumberCounter = new AtomicInteger();

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
        account.withdraw(DepositFee.forAccountType(account.getType()).apply(amount));
    }

    public String createNewAccount(AccountType accountType, BigDecimal initialBalance) {
        String accountNumber = Integer.toString(accountNumberCounter.incrementAndGet());
        return createNewAccount(accountNumber, accountType, initialBalance);
    }
}
