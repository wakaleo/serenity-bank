package com.bddinaction.serenitybank.glue;

import com.bddinaction.serenitybank.accounts.AccountDetails;
import com.bddinaction.serenitybank.accounts.AccountService;
import com.bddinaction.serenitybank.model.AccountType;
import com.google.common.collect.Maps;
import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.Transformer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.data.Percentage.withPercentage;

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
    public void transfersFunds(BigDecimal amount,
                               AccountType fromAccountType,
                               AccountType toAccountType) throws Throwable {
        AccountDetails fromAccount = accountsByType.get(fromAccountType);
        AccountDetails toAccount = accountsByType.get(toAccountType);

        accountService.transferFunds(fromAccount.getNumber(), toAccount.getNumber(), amount);
    }

    @Then("^he should have the following balances:$")
    public void heShouldHaveTheFollowingBalances(List<AccountDetails> accounts) throws Throwable {

        SoftAssertions softly = new SoftAssertions();

        accounts.forEach(
                account -> {
                    softly.assertThat(accountService.getBalance(account.getNumber())).isCloseTo(account.getBalance(), withPercentage(0.0));
                }
        );

        softly.assertAll();
    }

    @When("^he deposits €(\\d+) into his (.*) account$")
    public void heDepositsMoneyIntoHisCurrentAccount(BigDecimal amount,
                                                     AccountType type) throws Throwable {
        AccountDetails fromAccount = accountsByType.get(type);
        accountService.makeDeposit(fromAccount.getNumber(), amount);
    }

    @Given("^the distance from (.*) to (.*) is (\\d+) km$")
    public void pointsEarnedBetweenCities(String departure,
                                          String destination,
                                          int distance) throws Throwable {
    }

    public enum City {
        London("London"), Paris("Paris"), SanFrancisco("San Francisco"), SaintPetersbuug("St Petersburg");

        public final String name;

        City(String name) {
            this.name = name;
        }
    }

    public class CityConverter  extends Transformer<City> {
        @Override
        public City transform(String cityName) {
            return Arrays.stream(City.values())
                    .filter(city -> city.name.equalsIgnoreCase(cityName))
                    .findFirst()
                    .orElseThrow(() -> new UnknownCityException(cityName));
        }
    }

    public class UnknownCityException extends RuntimeException {
        public UnknownCityException(String message) {
            super(message);
        }
    }

    public enum CabinClass {
        Economy, Business, First
    }

    public class Itinerary {
        public final City departure;
        public final City destination;
        public final int distance;

        public Itinerary(City departure, City destination, int distance) {
            this.departure = departure;
            this.destination = destination;
            this.distance = distance;
        }
    }

    @Given("the following flight routes:")
    public void flightRoutes(List<Itinerary> flightRoutes) {
    }

    @When("^I fly from (.*) to (.*) in (.*)$")
    public void iFly(@Transform(CityConverter.class) City departure, City destination, CabinClass cabinClass) throws Throwable {
    }

    @Then("^I should earn (\\d+) points$")
    public void iShouldEarnPoints(int arg0) throws Throwable {
    }

    @Given("^Joe has a <account-type> account with an initial balance of <initial-balance>$")
    public void joeHasAAccountTypeAccountWithAnInitialBalanceOfInitialBalance() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^he deposits €<deposit> into his account$")
    public void heDeposits€DepositIntoHisAccount() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^his account should have a balance of €<final-balance>$")
    public void hisAccountShouldHaveABalanceOf€FinalBalance() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the following possible destinations: (.*)$")
    public void theFollowingPossibleDestinations(List<City> destinations) {
    }
}
