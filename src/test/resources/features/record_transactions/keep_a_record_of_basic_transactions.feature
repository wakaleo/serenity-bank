Feature: Keep a record of basic transactions
  In order to have visibility on my spending
  As an online banking customer
  I want to see the history of all the transactions on my account

  Scenario: View basic transactions in reverse chronological order
    Given Joe has a Current account with a balance of €1000
    When he deposits €100 into his account on 01/05/2017
    When he withdraws €50 from his account on 02/05/2017
    Then his transaction history should include:
      | amount | description | date       | balance |
      | 50     | withdrawal  | 02/05/2017 | 1050    |
      | 100    | deposit     | 01/05/2017 | 1100    |


