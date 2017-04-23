package com.bddinaction.serenitybank.glue;

import com.bddinaction.serenitybank.accounts.AccountService;
import com.bddinaction.serenitybank.accounts.model.Transaction;
import com.bddinaction.serenitybank.model.AccountType;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;

public class DepositSteps {

    AccountService accountService = new AccountService();
    String accountNumber;

    @Given("^Joe has a (.*) account with a balance of €(.*)$")
    public void joe_has_an_account_with_a_balance(AccountType accountType, BigDecimal initialBalance) throws Throwable {
        accountNumber = accountService.createNewAccount(accountType,initialBalance);
    }

    @Given("^Joe opened a (.*) account on (.*) with a balance of €(.*)$")
    public void joe_opened_an_account_with_a_balance(AccountType accountType,
                                                     @Format("dd/MM/yyyy") Date openingDate,
                                                     BigDecimal initialBalance) throws Throwable {
        accountNumber = accountService.createNewAccount(accountType,initialBalance, new LocalDate(openingDate));
    }

    @When("^he deposits €(\\d+) into his account$")
    public void he_deposits_€_into_his_account(BigDecimal deposit) throws Throwable {
        accountService.makeDeposit(accountNumber, deposit);
    }

    @Then("^his account should have a balance of €(.*)$")
    public void his_account_should_have_a_balance(BigDecimal expectedBalance) throws Throwable {
        assertThat(accountService.getBalance(accountNumber)).isCloseTo(expectedBalance, withPercentage(0.0));
    }

    @When("^he deposits €(\\d+) into his account on (.*)$")
    public void he_deposits_into_his_account(BigDecimal amount,
                                             @Format("dd/MM/yyyy") Date transactionDate) throws Throwable {
        accountService.makeDeposit(accountNumber, amount, new LocalDate(transactionDate));
    }

    @When("^he withdraws €(\\d+) from his account on (.*)$")
    public void he_withdraws_from_his_account(BigDecimal amount,
                                              @Format("dd/MM/yyyy") Date transactionDate) throws Throwable {
        accountService.makeWithdrawal(accountNumber, amount, new LocalDate(transactionDate));
    }

    @Then("^his transaction history should include:$")
    public void his_transaction_history_should_include(List<Transaction> transactions) throws Throwable {
        assertThat(accountService.getTransactions(accountNumber)).isEqualTo(transactions);
    }

}


