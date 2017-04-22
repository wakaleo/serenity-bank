package com.bddinaction.serenitybank.accounts

import org.joda.time.LocalDate
import spock.lang.Specification

import static com.bddinaction.serenitybank.model.AccountType.Current

class WhenDepositingFunds extends Specification {
    def "depositing funds into an account"() {
        given:
            def accountService = new AccountService();
            def accountNumber = accountService.createNewAccount(Current, 100.00)
            def depositDate = LocalDate.parse("2017-04-24")
        when:
            accountService.makeDeposit(accountNumber, 50.00, depositDate)
        then:
            accountService.getBalance(accountNumber) == 150.00
    }
}
