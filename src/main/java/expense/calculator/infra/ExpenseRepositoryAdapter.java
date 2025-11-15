package expense.calculator.infra;

import expense.calculator.domain.model.Expense;
import expense.calculator.domain.outgoing.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;

public class ExpenseRepositoryAdapter implements ExpenseRepository {

  private final JpaExpenseRepository jpaExpenseRepository;

  public ExpenseRepositoryAdapter(JpaExpenseRepository jpaExpenseRepository) {
    this.jpaExpenseRepository = jpaExpenseRepository;
  }

  @Override
  public Expense save(Expense expense) {

    ExpenseJPA expenseJPA = new ExpenseJPA();
    expenseJPA.setExpenseName(expense.getExpenseName());
    expenseJPA.setExpenseCategory(expense.getExpenseCategory());
    expenseJPA.setAmount(expense.getCost());
    expenseJPA.setDateAdded(expense.getDateAdded());
    expenseJPA.setDate(expense.getDate());

    ExpenseJPA saved = jpaExpenseRepository.save(expenseJPA);

    return new Expense(
        saved.getId(),
        saved.getExpenseName(),
        saved.getExpenseCategory(),
        saved.getAmount(),
        saved.getDateAdded(),
        saved.getDate());
  }

  @Override
  public Expense get(String id) {
    ExpenseJPA expenseJPA =
        jpaExpenseRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Expense does not exists for the id:" + id));

    return new Expense(
        expenseJPA.getId(),
        expenseJPA.getExpenseName(),
        expenseJPA.getExpenseCategory(),
        expenseJPA.getAmount(),
        expenseJPA.getDateAdded(),
        expenseJPA.getDate());
  }

  @Override
  public String delete(String id) {

    ExpenseJPA expenseJPA =
        jpaExpenseRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Expense does not exists for the id:" + id));

    jpaExpenseRepository.deleteById(expenseJPA.getId());
    return "Expense deleted successfully";
  }

  @Override
  public Expense update(Expense expense, String id) {
    ExpenseJPA expenseJPA =
        jpaExpenseRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Expense does not exists for the id:" + id));
    expenseJPA.setId(expenseJPA.getId());
    expenseJPA.setExpenseName(expense.getExpenseName());
    expenseJPA.setAmount(expense.getCost());
    expenseJPA.setDate(LocalDate.now());

    ExpenseJPA saved = jpaExpenseRepository.save(expenseJPA);

    return new Expense(
        saved.getId(),
        saved.getExpenseName(),
        saved.getExpenseCategory(),
        saved.getAmount(),
        saved.getDateAdded(),
        saved.getDate());
  }
}
