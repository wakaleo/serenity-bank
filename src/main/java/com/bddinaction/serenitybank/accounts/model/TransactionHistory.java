package com.bddinaction.serenitybank.accounts.model;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionHistory {

    private List<Transaction> transactions = new ArrayList<>();

    private final AtomicInteger transactionCounter = new AtomicInteger(1);

    public List<Transaction> getTransactions() {
        return ImmutableList.copyOf(transactions);
    }

    public void add(Transaction transaction) {
        transactions.add(transaction.withNumber(transactionCounter.getAndIncrement()));
    }
}
