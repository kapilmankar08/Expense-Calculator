package expense.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import expense.calculator.domain.incoming.ExpenseUseCase;
import expense.calculator.domain.model.Expense;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExpenseSteps {

  private final ExpenseUseCase expenseUseCase;

  private Expense expense;
  private String msg;
  private final Map<String, UUID> uuidMap = new HashMap<>();

  public ExpenseSteps(ExpenseUseCase expenseUseCase) {
    this.expenseUseCase = expenseUseCase;
  }

  @Given("there exists a expense with the following details")
  public void givenThereExistsAnExpense(Map<String, String> row) {
    String category = row.get("category");
    String expenseName = row.get("expenseName");
    String amt = row.get("amount");

    this.expense = expenseUseCase.createExpense(category, expenseName, Integer.parseInt(amt));
  }

  @Given("assign the identifier a name {string}")
  public void givenAssignTheIdentifierANameExistingExpenseId(String expenseName) {
    this.uuidMap.put(expenseName, UUID.fromString(this.expense.getId()));
  }

  @When("User adds the expense with following details")
  public void whenAddsTheExpense(Map<String, String> row) {
    String category = row.get("category");
    String expenseName = row.get("expenseName");
    String amt = row.get("amount");

    this.expense = expenseUseCase.createExpense(category, expenseName, Integer.parseInt(amt));
  }

  @When("getting the expense by providing the UUID of the expense {string}")
  public void whenGettingTheExpense(String uuid) {

    String name = this.uuidMap.get(uuid).toString();

    this.expense = expenseUseCase.getExpense(name);
  }

  @When("updating the {string} with the following details")
  public void whenEditingTheDetails(String expenseName, Map<String, String> row) {

    this.expense =
        expenseUseCase.updateExpense(
            row.get("expenseName"),
            Integer.parseInt(row.get("amount")),
            this.uuidMap.get(expenseName).toString());
  }

  @When("deleting the {string}")
  public void whenEditingTheDetails(String name) {

    this.msg = expenseUseCase.deleteExpense(this.uuidMap.get(name).toString());
  }

  @Then("expect the expense added with response:")
  public void thenExpectTheExpense(Map<String, String> row) {
    String category = row.get("category");
    String expenseName = row.get("expenseName");
    String amt = row.get("amount");

    assertEquals(expense.getExpenseName(), expenseName);
    assertEquals(expense.getExpenseCategory(), category);
    assertEquals(expense.getCost(), Integer.parseInt(amt));

    if (row.get("dateAdded").equals("now")) {
      OffsetDateTime now = OffsetDateTime.now();
      OffsetDateTime dateAdded = expense.getDateAdded();

      Duration duration = Duration.between(dateAdded, now);

      assertTrue(
          "DateAdded should be within 10 seconds of the current time",
          Math.abs(duration.getSeconds()) <= 10);
    }
  }

  @Then("expect the response contain the following details:")
  public void thenTheResponseContainTheFollowingDetails(Map<String, String> row) {
    LocalDate expectdDate = LocalDate.parse(row.get("date"));

    String category = row.get("category");
    String expenseName = row.get("expenseName");
    String amt = row.get("amount");

    assertEquals(expense.getExpenseName(), expenseName);
    assertEquals(expense.getExpenseCategory(), category);
    assertEquals(expense.getCost(), Integer.parseInt(amt));
    assertEquals(expense.getDate(), expectdDate);
  }

  @Then("expect the expense is deleted with message {string}")
  public void expectExpenseDelete(String expecteMsg) {
    assertEquals(this.msg, expecteMsg);
  }

  @Then("expect the result is an error with message {string}")
  public void expectTheResultIsAnError(String msg) {
    assertEquals(msg, expense.toString());
  }

  @Then("expect the expense {string} is updated with the details:")
  public void thenExpectTheExpenseIsUpdatedWithTheDetails(
      String expenseName, Map<String, String> row) {
    String category = row.get("category");
    String updatedExpenseName = row.get("expenseName");
    String amt = row.get("amount");

    Expense result = expenseUseCase.getExpense(this.uuidMap.get(expenseName).toString());
    assertEquals(result.getExpenseName(), updatedExpenseName);
    assertEquals(result.getExpenseCategory(), category);
    assertEquals(result.getCost(), Integer.parseInt(amt));
  }
}
