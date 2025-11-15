package expense.calculator.application;

import expense.calculator.domain.model.Expense;

public class ExpenseMapper {

  public static ExpenseResponse toResponse(Expense expense) {
    return new ExpenseResponse(
        expense.getId(),
        expense.getExpenseName(),
        expense.getExpenseCategory(),
        expense.getCost(),
        expense.getDateAdded(),
        expense.getDate());
  }
}
