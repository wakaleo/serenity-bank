package com.bddinaction.serenitybank.accounts;

import com.bddinaction.serenitybank.model.BankAccount;

import java.util.Comparator;

class AccountOrdering {

    private static final Comparator<BankAccount> ACCOUNT_COMPARATOR = Comparator.comparing(account -> account.getNumber());

    static BankAccount firstOf(BankAccount fromAccount, BankAccount toAccount) {
        return isBefore(fromAccount,toAccount) ? fromAccount : toAccount;
    }

    static BankAccount lastOf(BankAccount fromAccount, BankAccount toAccount) {
        return isBefore(fromAccount,toAccount) ? toAccount : fromAccount;
    }

    private static boolean isBefore(BankAccount firstAccount, BankAccount lastAcount) {
        return (ACCOUNT_COMPARATOR.compare(firstAccount,lastAcount) <= 0);
    }
}
