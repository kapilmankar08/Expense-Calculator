package expense.calculator.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class Expense {

  // TODO: change variable names
  private final String id;
  private final String expenseName;
  private final String expenseCategory;
  private final int cost;
  private final OffsetDateTime dateAdded;
  private final LocalDate date;

  public Expense(
      String id,
      String expenseName,
      String expenseCategory,
      int cost,
      OffsetDateTime dateAdded,
      LocalDate date) {
    this.id = id;
    this.expenseName = expenseName;
    this.expenseCategory = expenseCategory;
    this.cost = cost;
    this.dateAdded = dateAdded;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public String getExpenseName() {
    return expenseName;
  }

  public int getCost() {
    return cost;
  }

  public OffsetDateTime getDateAdded() {
    return dateAdded;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getExpenseCategory() {
    return expenseCategory;
  }
}
