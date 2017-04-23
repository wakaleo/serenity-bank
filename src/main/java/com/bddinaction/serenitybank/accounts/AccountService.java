package com.bddinaction.serenitybank.accounts;

import com.bddinaction.serenitybank.accounts.model.Transaction;
import com.bddinaction.serenitybank.deposits.DepositFee;
import com.bddinaction.serenitybank.model.AccountType;
import com.bddinaction.serenitybank.model.BankAccount;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.bddinaction.serenitybank.accounts.AccountOrdering.firstOf;
import static com.bddinaction.serenitybank.accounts.AccountOrdering.lastOf;

public class AccountService {

    Map<String, BankAccount> accounts = new ConcurrentHashMap<>();

    AtomicInteger accountNumberCounter = new AtomicInteger();

    public String createNewAccount(String accountNumber, AccountType type, BigDecimal initialDeposit) {
        return createNewAccount(accountNumber, type, initialDeposit, LocalDate.now());
    }

    public String createNewAccount(String accountNumber,
                                   AccountType type,
                                   BigDecimal initialDeposit,
                                   LocalDate openingDate) {
        BankAccount newAccount = new BankAccount(accountNumber,type);
        newAccount.deposit(initialDeposit, openingDate);
        accounts.put(accountNumber, newAccount);
        return accountNumber;
    }

    public void transferFunds(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {

        BankAccount fromAccount = accounts.get(fromAccountNumber);
        BankAccount toAccount = accounts.get(toAccountNumber);

        synchronized (firstOf(fromAccount, toAccount)) {
            synchronized (lastOf(fromAccount, toAccount)) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount,  LocalDate.now());
            }
        }
    }

    public BigDecimal getBalance(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        return account.getBalance();
    }

    public void makeDeposit(String accountNumber, BigDecimal amount) {
        makeDeposit(accountNumber, amount, LocalDate.now());
    }

    public void makeDeposit(String accountNumber, BigDecimal amount, LocalDate depositDate) {
        BankAccount account = accounts.get(accountNumber);
        account.deposit(amount, depositDate);
        account.withdraw(DepositFee.forAccountType(account.getType()).apply(amount), depositDate, "deposit fee");
    }

    public String createNewAccount(AccountType accountType, BigDecimal initialBalance) {
        return createNewAccount(accountType, initialBalance, LocalDate.now());
    }

    public String createNewAccount(AccountType accountType, BigDecimal initialBalance, LocalDate openingDate) {
        String accountNumber = Integer.toString(accountNumberCounter.incrementAndGet());
        return createNewAccount(accountNumber, accountType, initialBalance, openingDate);
    }

    public void makeWithdrawal(String accountNumber,
                               BigDecimal amount,
                               LocalDate date) {
        BankAccount account = accounts.get(accountNumber);
        account.withdraw(amount, date);
    }

    public List<Transaction> getTransactions(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        return account.getTransactionHistory()
                .stream()
                .sorted(Comparator.comparing(Transaction::getNumber).reversed())
                .collect(Collectors.toList());
    }
}
