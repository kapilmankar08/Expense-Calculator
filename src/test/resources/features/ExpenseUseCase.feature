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
    When getting the expense by providing the UUID 09fe2b32-ab35-4c70-975e-d466f55b3814
    Then expect the response contain the following details:
      | attribute   | value      |
      | expenseName | Dominoes   |
      | category    | Food       |
      | amount      | 2000       |
      | date        | 2025-10-21 |