package expense.calculator.infra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaExpenseRepository extends JpaRepository<ExpenseJPA, String> {}
