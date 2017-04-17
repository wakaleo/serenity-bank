package com.bddinaction.serenitybank.accounts

import spock.lang.Specification

import static com.bddinaction.serenitybank.model.AccountType.BasicSavings
import static com.bddinaction.serenitybank.model.AccountType.Current

class WhenTransferingFundsBetweenAccounts extends Specification {

    final CURRENT_ACCOUNT_NUMBER = "100001"
    final SAVINGS_ACCOUNT_NUMBER = "100002"

    def "the account balances should be correctly updated"() {
        given:
            def accountService = new AccountService();
        and:
            accountService.createNewAccount(CURRENT_ACCOUNT_NUMBER, Current, 1000.00)
            accountService.createNewAccount(SAVINGS_ACCOUNT_NUMBER, BasicSavings, 1000.00)
        when:
            accountService.transferFunds(CURRENT_ACCOUNT_NUMBER,SAVINGS_ACCOUNT_NUMBER, 100)
        then:
            accountService.getBalance(CURRENT_ACCOUNT_NUMBER) == 900.00
            accountService.getBalance(SAVINGS_ACCOUNT_NUMBER) == 1100.00
    }
}
