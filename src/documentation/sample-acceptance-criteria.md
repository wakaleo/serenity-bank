# Exercise 2 - Sample Acceptance Criteria

The requirements provided by the client are:

_"There are two types of savings accounts: Basic Saver accounts and BigSaver accounts. Both have a €0.50 fee when you make a cash deposit of under €100. For cash sums of over €100, there is a €1.00 fee for the Basic Saver accounts and a €0.75 fee for a Big Saver account. For over €1000, there is a fixed €1.25 fee. If a customer deposits more than €2000 in cash in one go, or a total of €5000 in the space of a month, the account will be flagged for review."_

## Sample Acceptance Criteria

### Savings accounts have deposit fees that vary depending on the account type and deposit amount

Savings accounts have the following deposit fees

    | Amount        | Account Type | Deposit Fee |    
    | ------------- | ------------ | ----------- |
    | Under €100    | All types    | €0.50       |
    | €100 to €1000 | Basic Saver  | €1.00       |
    | €100 to €1000 | Big Saver    | €0.75       |
    | Over €1000    | All types    | €1.25       |

### Accounts with large cash deposits are subject to Anti Money Laundering Reviews

- If a cash deposit greater than €2000 is made in any savings account, the account will be flagged for review
  
  _Examples:_
        
     - A cash deposit of €2500 made into a Basic Saver account
     - A cash deposit of €5000 made into a Big Saver account
    
- If cash deposits for a total of over €5000 are made into any savings account over a week, the account will be flagged for review.

  _Examples:_
     - A cash deposit of €2500 made into a Basic Saver account should trigger a review
     - A cash deposit of €1900 made into a Basic Saver account should not trigger a review
     - Four cash deposits of €1500 made on subsequent days should trigger a review
     - Four cash deposits of €1500 made every two weeks should not trigger a review
