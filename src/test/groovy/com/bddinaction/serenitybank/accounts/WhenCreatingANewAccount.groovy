package com.bddinaction.serenitybank.accounts

import spock.lang.Specification

import static com.bddinaction.serenitybank.model.AccountType.Current

class WhenCreatingANewAccount extends Specification {

    final CURRENT_ACCOUNT_NUMBER = "100001"

    def "the initial account balance should correspond to the amount deposited"() {
        given:
            def accountService = new AccountService();
        when:
            accountService.createNewAccount(CURRENT_ACCOUNT_NUMBER, Current, 100.00)
        then:
            accountService.getBalance(CURRENT_ACCOUNT_NUMBER) == 100.00
    }
}
