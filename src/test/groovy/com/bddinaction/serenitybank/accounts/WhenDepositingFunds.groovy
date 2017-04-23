package com.bddinaction.serenitybank.accounts

import org.joda.time.LocalDate
import spock.lang.Specification

import static com.bddinaction.serenitybank.model.AccountType.Current

class WhenDepositingFunds extends Specification {

    final def ACCOUNT_CREATION_DATE = new LocalDate()
    final def TRANSACTION_DATE = ACCOUNT_CREATION_DATE.plusDays(2)

    def "depositing funds into an account"() {
        given:
            def accountService = new AccountService();
            def accountNumber = accountService.createNewAccount(Current, 100.00)
        when:
            accountService.makeDeposit(accountNumber, 50.00, TRANSACTION_DATE)
        then:
            accountService.getBalance(accountNumber) == 150.00
    }
}
