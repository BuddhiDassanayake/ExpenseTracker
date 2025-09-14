Feature: Expense Tracker Service

  Scenario: Add a valid expense
    Given the expense repository is empty
    When I add an expense with description "Milk" and amount 2.50
    Then the expense should be saved
    And the total number of expenses should be 1

  Scenario: Attempt to add an expense with a negative amount
    Given the expense repository is empty
    When I attempt to add an expense with description "Invalid" and amount -10.0
    Then an IllegalArgumentException should be thrown

  Scenario: Get total expenses
    Given the expense repository contains the following expenses:
      | description | amount |
      | Coffee      | 3.00   |
      | Bread       | 1.80   |
    When I get the total expenses
    Then the total amount should be 4.80