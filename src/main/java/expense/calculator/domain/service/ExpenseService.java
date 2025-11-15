package expense.calculator.domain.service;

import expense.calculator.domain.incoming.ExpenseUseCase;
import expense.calculator.domain.model.Expense;
import expense.calculator.domain.outgoing.ExpenseRepository;
import java.time.*;
import java.util.UUID;

public class ExpenseService implements ExpenseUseCase {

  private final ExpenseRepository expenseRepository;

  public ExpenseService(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public Expense createExpense(String category, String expenseName, int amount) {

    Expense expense =
        new Expense(
            UUID.randomUUID().toString(),
            expenseName,
            category,
            amount,
            OffsetDateTime.now(),
            LocalDate.now());

    return expenseRepository.save(expense);
  }

  @Override
  public Expense getExpense(String id) {
    return expenseRepository.get(id);
  }

  @Override
  public String deleteExpense(String id) {
    return expenseRepository.delete(id);
  }

  @Override
  public Expense updateExpense(String expenseName, int amount, String id) {

    Expense existingExpense = expenseRepository.get(id);

    Expense expense =
        new Expense(
            existingExpense.getId(),
            expenseName,
            existingExpense.getExpenseCategory(),
            amount,
            existingExpense.getDateAdded(),
            existingExpense.getDate());

    return expenseRepository.update(expense, existingExpense.getId());
  }
}
