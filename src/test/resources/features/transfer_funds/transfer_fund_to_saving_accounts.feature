Feature: Transfer funds to savings accounts

  In order to invest my savings
  As an online banking customer
  I want to transfer some of my disposable income to an interest-earning savings account

  There are two types of savings accounts: basic and fixed term.
  Deposits to fixed term accounts must be at least €1000

  Scenario: Tranfer funds to a basic savings account
    Given Joe has the following accounts:
      | Number | Type         | Balance |
      | 123456 | Current      | 1000    |
      | 123457 | BasicSavings | 2000    |
    When he transfers €100 from his Current account to his BasicSavings account
    Then he should have the following balances:
      | Number | Type         | Balance |
      | 123456 | Current      | 900     |
      | 123457 | BasicSavings | 2100    |
