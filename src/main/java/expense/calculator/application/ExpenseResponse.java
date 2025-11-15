package expense.calculator.application;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class ExpenseResponse {

  private final String id;
  private final String category;
  private final String expenseName;
  private final int amount;
  private final OffsetDateTime dateAdded;
  private final LocalDate date;

  public ExpenseResponse(
      String id,
      String category,
      String expenseName,
      int amount,
      OffsetDateTime dateAdded,
      LocalDate date) {
    this.id = id;
    this.category = category;
    this.expenseName = expenseName;
    this.amount = amount;
    this.dateAdded = dateAdded;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public String getExpenseName() {
    return expenseName;
  }

  public String getCategory() {
    return category;
  }

  public int getAmount() {
    return amount;
  }

  public OffsetDateTime getDateAdded() {
    return dateAdded;
  }

  public LocalDate getDate() {
    return date;
  }
}
