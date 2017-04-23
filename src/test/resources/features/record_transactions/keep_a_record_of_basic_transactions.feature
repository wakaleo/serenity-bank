Feature: Keep a record of basic transactions
  In order to have visibility on my spending
  As an online banking customer
  I want to see the history of all the transactions on my account

  Scenario: View basic transactions in reverse chronological order
    Given Joe opened a Current account on 01/05/2017 with a balance of €1000
    When he deposits €100 into his account on 05/05/2017
    When he withdraws €50 from his account on 08/05/2017
    Then his transaction history should include:
      | amount | description | date       | balance |
      | -50    | withdrawal  | 2017-05-08 | 1050    |
      | 100    | deposit     | 2017-05-05 | 1100    |
      | 1000   | deposit     | 2017-05-01 | 1000    |


