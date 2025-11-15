package expense.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import expense.calculator.domain.incoming.ExpenseUseCase;
import expense.calculator.domain.model.Expense;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Map;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExpenseSteps {

  private final ExpenseUseCase expenseUseCase;

  private Expense expense;
  private String msg;

  public ExpenseSteps(ExpenseUseCase expenseUseCase) {
    this.expenseUseCase = expenseUseCase;
  }

  @When("User adds the expense with following details")
  public void whenAddsTheExpense(Map<String, String> row) {
    String category = row.get("category");
    String expenseName = row.get("expenseName");
    String amt = row.get("amount");

    this.expense = expenseUseCase.createExpense(category, expenseName, Integer.parseInt(amt));
  }

  @When("getting the expense by providing the UUID {word}")
  public void whenGettingTheExpense(String uuid) {
    this.expense = expenseUseCase.getExpense(uuid);
  }

  @When("editing the expense by providing the UUID {word} with details:")
  public void whenEditingTheDetails(String uuid, Map<String, String> row) {

    this.expense =
        expenseUseCase.updateExpense(
            row.get("expenseName"), Integer.parseInt(row.get("amount")), uuid);
  }

  @When("deleting an expense by the UUID {word}")
  public void whenEditingTheDetails(String uuid) {

    this.msg = expenseUseCase.deleteExpense(uuid);
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
}
