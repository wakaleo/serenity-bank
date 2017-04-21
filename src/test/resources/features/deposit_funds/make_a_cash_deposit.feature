Feature: Make a cash deposit

  In order to be able to use my earnings for online payments
  As an online banking customer
  I want to deposit my hard-earned cash into my accounts

  Scenario: Deposit into a current account
  For current accounts, there is no fee.

    Given Joe has the following accounts:
      | Number | Type    | Balance |
      | 123456 | Current | 1000    |
    When he deposits €100 into his Current account
    Then he should have the following balances:
      | Number | Type    | Balance |
      | 123456 | Current | 1100    |

  Scenario: Deposit into a basic savings account
  For all savings accounts, there is a €0.50 fee up to €100.

    Given Joe has the following accounts:
      | Number | Type         | Balance |
      | 123456 | BasicSavings | 1000    |
    When he deposits €50 into his BasicSavings account
    Then he should have the following balances:
      | Number | Type         | Balance |
      | 123456 | BasicSavings | 1049.50 |

  Scenario: Deposit into a super savings account
  For all savings accounts, there is a €0.50 fee up to €100.

    Given Joe has the following accounts:
      | Number | Type     | Balance |
      | 123456 | BigSaver | 1000    |
    When he deposits €100 into his BigSaver account
    Then he should have the following balances:
      | Number | Type     | Balance |
      | 123456 | BigSaver | 1099.50 |
