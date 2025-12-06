package expense.calculator.infra;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity(name = "expense_table")
public class ExpenseJPA {

  @Id
  @GeneratedValue(generator = "uuid2")
  @Column(name = "id", updatable = false, nullable = false)
  private String id;

  private String expenseCategory;
  private String expenseName;
  private int amount;
  private OffsetDateTime dateAdded;
  private LocalDate date;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getExpenseName() {
    return expenseName;
  }

  public void setExpenseName(String expenseName) {
    this.expenseName = expenseName;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public OffsetDateTime getDateAdded() {
    return dateAdded;
  }

  public void setDateAdded(OffsetDateTime dateAdded) {
    this.dateAdded = dateAdded;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getExpenseCategory() {
    return expenseCategory;
  }

  public void setExpenseCategory(String expenseCategory) {
    this.expenseCategory = expenseCategory;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
