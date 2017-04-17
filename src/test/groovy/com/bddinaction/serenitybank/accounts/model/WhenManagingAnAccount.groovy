package com.bddinaction.serenitybank.accounts.model

import com.bddinaction.serenitybank.model.AccountType
import com.bddinaction.serenitybank.model.BankAccount
import spock.lang.Specification


class WhenManagingAnAccount extends Specification {

    def "the initial balance of a current account should be 0"() {
        when:
            def currentAccount = new BankAccount("123456", AccountType.Current)
        then:
            currentAccount.balance == 0.0
    }

    def "depositing a sum into the account should update the balance"() {
        given:
            def currentAccount = new BankAccount("123456", AccountType.Current)
        when:
            currentAccount.deposit(1000)
        then:
            currentAccount.balance == 1000
    }

}