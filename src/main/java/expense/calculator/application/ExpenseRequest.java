package expense.calculator.application;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ExpenseRequest {

  @NotBlank(message = "Expense description cannot be empty")
  private String expenseToBeAdded;

  @NotBlank(message = "Category cannot be empty")
  private String category;

  @NotNull(message = "Amount cannot be null")
  @Min(value = 1, message = "Amount must be greater than 0")
  private Integer amount;

  public String getExpenseToBeAdded() {
    return expenseToBeAdded;
  }

  public void setExpenseToBeAdded(String expenseToBeAdded) {
    this.expenseToBeAdded = expenseToBeAdded;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
