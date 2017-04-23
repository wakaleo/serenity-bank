package com.bddinaction.serenitybank.deposits

import spock.lang.Specification
import spock.lang.Unroll

import static com.bddinaction.serenitybank.model.AccountType.*

class WhenCalculatingDepositFees extends Specification {

    @Unroll
    def "a deposit fee of #expectedDepositFee should be charged when depositing €#amount into a #accountType account"() {
        expect:
        DepositFee.forAccountType(accountType).apply(amount) == expectedDepositFee
        where:

        accountType  | amount  | expectedDepositFee
        Current      | 1000.0  | 0.0
        // Basic savings
        BasicSavings | 10.00   | 0.50
        BasicSavings | 100.00  | 0.50
        BasicSavings | 100.01  | 1.00
        BasicSavings | 1000.00 | 1.00
        BasicSavings | 1000.01 | 1.25
        BasicSavings | 1500.00 | 1.25
        // Big Saver
        BigSaver     | 10.00   | 0.50
        BigSaver     | 100.00  | 0.50
        BigSaver     | 100.01  | 0.75
        BigSaver     | 1000.00 | 0.75
        BigSaver     | 1000.01 | 1.25
        BigSaver     | 1500.00 | 1.25
    }
}