package expense.calculator.domain.incoming;

import expense.calculator.domain.model.Expense;

// Create DTOs, should return dtos instead of domain object
public interface ExpenseUseCase {

//    change int
    Expense createExpense(String category, String expenseName, int amount);

    Expense getExpense(String id);

    String deleteExpense(String id);

    Expense updateExpense(String expenseName, int amount, String id);
}
