package com.bddinaction.serenitybank.glue;

import com.bddinaction.serenitybank.accounts.AccountDetails;
import com.bddinaction.serenitybank.accounts.AccountService;
import com.bddinaction.serenitybank.model.AccountType;
import com.google.common.collect.Maps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferFundsSteps {

    Map<AccountType, AccountDetails> accountsByType = new HashMap<>();
    AccountService accountService = new AccountService();

    @Given("^(?:.*) has the following accounts:")
    public void customerHasAccounts(List<AccountDetails> accounts) {

        accountsByType = Maps.uniqueIndex(accounts, AccountDetails::getType);

        accounts.forEach(
                account -> accountService.createNewAccount(account.getNumber(),
                                                           account.getType(),
                                                           account.getBalance())
        );
    }

    @When("^he transfers €(\\d+) from his (.*) account to his (.*) account$")
    public void transfersFunds(BigDecimal amount, AccountType fromAccountType, AccountType toAccountType) throws Throwable {
        AccountDetails fromAccount = accountsByType.get(fromAccountType);
        AccountDetails toAccount = accountsByType.get(toAccountType);

        accountService.transferFunds(fromAccount.getNumber(), toAccount.getNumber(), amount);
    }

    @Then("^he should have the following balances:$")
    public void heShouldHaveTheFollowingBalances(List<AccountDetails> accounts) throws Throwable {

        SoftAssertions softly = new SoftAssertions();

        accounts.forEach(
                account -> {
                    softly.assertThat(accountService.getBalance(account.getNumber())).isEqualTo(account.getBalance());
                }
        );

        softly.assertAll();
    }
}
