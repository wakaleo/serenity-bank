package com.bddinaction.serenitybank.accounts

import org.joda.time.LocalDate
import spock.lang.Specification

import static com.bddinaction.serenitybank.model.AccountType.Current

class WhenWithdrawingFunds extends Specification {
    def "withdrawing funds from an account"() {
        given:
            def accountService = new AccountService();
            def accountNumber = accountService.createNewAccount(Current, 100.00)
            def depositDate = LocalDate.parse("2017-04-24")
        when:
            accountService.makeWithdrawal(accountNumber, 50.00, depositDate)
        then:
            accountService.getBalance(accountNumber) == 50.00
    }
}
