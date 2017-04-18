Feature: Make a cash deposit

  In order to be able to use my earnings for online payments
  As an online banking customer
  I want to deposit my hard-earned cash into my accounts

  Customers can make a cash deposit into their accounts.
  For current accounts, there is no fee. For savings accounts,
  there is a €0.50 fee for each deposit.

  Scenario: Deposit into a current account
    Given Joe has the following accounts:
      | Number | Type         | Balance |
      | 123456 | Current      | 1000    |
    When he deposits €100 into his Current account
    Then he should have the following balances:
      | Number | Type         | Balance |
      | 123456 | Current      | 1100    |


