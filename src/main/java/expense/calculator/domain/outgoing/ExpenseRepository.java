package expense.calculator.domain.outgoing;

import expense.calculator.domain.model.Expense;

// use dtos
public interface ExpenseRepository {

    Expense save(Expense expense);

    Expense get(String id);

    String delete(String id);

    Expense update(Expense expense, String id);
}
