package com.bddinaction.serenitybank.accounts

import com.bddinaction.serenitybank.accounts.model.Transaction
import org.joda.time.LocalDate
import spock.lang.Specification

import static com.bddinaction.serenitybank.model.AccountType.BasicSavings
import static com.bddinaction.serenitybank.model.AccountType.Current

class WhenRecordingTransactions extends Specification {

    final def ACCOUNT_CREATION_DATE = new LocalDate()
    final def TRANSACTION_DATE = ACCOUNT_CREATION_DATE.plusDays(2)
    final def NEXT_TRANSACTION_DATE = ACCOUNT_CREATION_DATE.plusDays(3)

    def "depositing into an account should record a deposit transaction"() {
        given:
            def accountService = new AccountService();
            def accountNumber = accountService.createNewAccount(Current, 100.00)
        when:
            accountService.makeDeposit(accountNumber, 50.00, TRANSACTION_DATE)
        then:
            accountService.getTransactions(accountNumber) ==
                    [Transaction.of(50.00).withDescription("deposit").on(TRANSACTION_DATE).andNewBalance(150.00),
                     Transaction.of(100.00).withDescription("deposit").on(ACCOUNT_CREATION_DATE).andNewBalance(100.00)]
    }

    def "depositing into an account should record a deposit transaction and the transaction fee"() {
        given:
            def accountService = new AccountService();
            def accountNumber = accountService.createNewAccount(BasicSavings, 100.00)
        when:
            accountService.makeDeposit(accountNumber, 50.00, TRANSACTION_DATE)
        then:
            accountService.getTransactions(accountNumber) ==
                    [Transaction.of(-0.50).withDescription("deposit fee").on(TRANSACTION_DATE).andNewBalance(149.50),
                     Transaction.of(50.00).withDescription("deposit").on(TRANSACTION_DATE).andNewBalance(150.00),
                     Transaction.of(100.00).withDescription("deposit").on(ACCOUNT_CREATION_DATE).andNewBalance(100.00)]
    }

    def "withdrawals from an account should record a withdrawal transaction"() {
        given:
            def accountService = new AccountService();
            def accountNumber = accountService.createNewAccount(Current, 100.00)
        when:
            accountService.makeDeposit(accountNumber, 50.00, TRANSACTION_DATE)
            accountService.makeWithdrawal(accountNumber, 25.00, NEXT_TRANSACTION_DATE)
        then:
            accountService.getTransactions(accountNumber) ==
                    [Transaction.of(-25.00).withDescription("withdrawal").on(NEXT_TRANSACTION_DATE).andNewBalance(125.00),
                     Transaction.of(50.00).withDescription("deposit").on(TRANSACTION_DATE).andNewBalance(150.00),
                     Transaction.of(100.00).withDescription("deposit").on(ACCOUNT_CREATION_DATE).andNewBalance(100.00)]
    }

}
