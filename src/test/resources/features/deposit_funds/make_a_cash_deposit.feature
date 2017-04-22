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

  Scenario: Deposit small amounts into a basic savings account
  For all savings accounts, there is a €0.50 fee up to €100.

    Given Joe has the following accounts:
      | Number | Type         | Balance |
      | 123456 | BasicSavings | 1000    |
    When he deposits €50 into his BasicSavings account
    Then he should have the following balances:
      | Number | Type         | Balance |
      | 123456 | BasicSavings | 1049.50 |

  Scenario: Deposit small amounts into a super savings account
  For all savings accounts, there is a €0.50 fee up to €100.

    Given Joe has the following accounts:
      | Number | Type     | Balance |
      | 123456 | BigSaver | 1000    |
    When he deposits €100 into his BigSaver account
    Then he should have the following balances:
      | Number | Type     | Balance |
      | 123456 | BigSaver | 1099.50 |

  Scenario: Economy flights earn points by distance
    Given the distance from London to Paris is 344 km
    Given the following possible destinations: London, Paris, Amsterdam
    When I fly from London to Paris in Economy
    Then I should earn 344 points


  Scenario Outline:
    Given the following flight routes:
      | departure | destination | distance |
      | London    | Paris       | 344      |
      | Paris     | Krakow      | 1500     |
      | London    | Budapest    | 1600     |
    When I fly from <departure> to <destination> in Economy
    Then I should earn <points> points
    And I should earn <status-points> status points

    Examples:
      | departure | destination | points | status-points |
      | London    | Paris       | 344    | 3             |
      | Paris     | Krakow      | 1500   | 15            |
      | London    | Budapest    | 1600   | 16            |


#
#  Scenario Outline: Deposit larger cash deposits
#    Given Joe has a <account-type> account with an initial balance of <initial-balance>
#    When he deposits €<deposit> into his account
#    Then his account should have a balance of €<final-balance>
#    Examples:
#      | account-type | initial-balance | deposit | final-balance | rule                       |
#      | BasicSavings | 1000            | 200     | 1199          | €1.00 fee for basic savers |
