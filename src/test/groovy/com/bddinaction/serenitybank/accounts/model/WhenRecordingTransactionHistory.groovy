package com.bddinaction.serenitybank.accounts.model

import org.joda.time.LocalDate
import spock.lang.Specification

class WhenRecordingTransactionHistory extends Specification {

    final def TRANSACTION_DATE = LocalDate.parse("2017-02-05")

    def "a transaction history starts with no transactions"() {
        when:
            TransactionHistory history = new TransactionHistory();
        then:
            history.transactions.isEmpty()
    }

    def "a new transaction can be added to the history"() {
        given:
            TransactionHistory history = new TransactionHistory();
        when:
            history.add(Transaction.of(100.00).on(TRANSACTION_DATE).withDescription("deposit").andNewBalance(100.00))
        then:
            history.transactions.size() == 1

    }

    def "the transaction order should be recorded in a unique counter starting from 1"() {
        given:
            TransactionHistory history = new TransactionHistory();
            def transaction1 = Transaction.of(100.00).on(TRANSACTION_DATE).withDescription("deposit").andNewBalance(100.00)
            def transaction2 = Transaction.of(50.00).on(TRANSACTION_DATE).withDescription("deposit").andNewBalance(150.00)
            def transaction3 = Transaction.of(25.00).on(TRANSACTION_DATE).withDescription("deposit").andNewBalance(175.00)
        when:
            history.add(transaction1)
            history.add(transaction2)
            history.add(transaction3)
        then:
            history.transactions.collect { it.amount } == [100.00, 50.00, 25.00]
            history.transactions.collect { it.number } == [1, 2, 3]
    }

}
