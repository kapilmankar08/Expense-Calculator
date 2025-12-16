Feature: Test Expense User Case

  #################################
  # Functional Scenarios
  #################################

  Scenario: User retrieves to add an expense(POST)
    When User adds the expense with following details
      | attribute   | value    |
      | expenseName | Dominoes |
      | category    | Food     |
      | amount      | 12000    |
    Then expect the expense added with response:
      | attribute   | value    |
      | expenseName | Dominoes |
      | category    | Food     |
      | amount      | 12000    |
      | dateAdded   | now      |

  Scenario: User retrieves an expense(GET)
    Given there exists a expense with the following details
      | expenseName | Taco Bell |
      | category    | Food      |
      | amount      | 1000      |
    And assign the identifier a name "existingExpense"
    When getting the expense by providing the UUID of the expense "existingExpense"
    Then expect the expense added with response:
      | attribute   | value     |
      | expenseName | Taco Bell |
      | category    | Food      |
      | amount      | 1000      |
      | dateAdded   | now       |

  Scenario: User updates an expense (PATCH)
    Given there exists a expense with the following details
      | expenseName | Pizza Hut |
      | category    | Food      |
      | amount      | 1000      |
    And assign the identifier a name "existingExpense"
    When updating the "existingExpense" with the following details
      | attribute   | value |
      | expenseName | KFC   |
      | amount      | 1500  |
    Then expect the expense "existingExpense" is updated with the details:
      | attribute   | value |
      | expenseName | KFC   |
      | category    | Food  |
      | amount      | 1500  |
      | dateAdded   | now   |

  Scenario: User delets an expense (DELETE)
    Given there exists a expense with the following details
      | expenseName | Pizza Hut |
      | category    | Food      |
      | amount      | 1000      |
    And assign the identifier a name "existingExpense"
    When deleting the "existingExpense"
    Then expect the expense is deleted with message "Expense deleted successfully"
