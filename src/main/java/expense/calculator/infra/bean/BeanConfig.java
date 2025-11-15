package expense.calculator.infra.bean;

import expense.calculator.domain.outgoing.ExpenseRepository;
import expense.calculator.domain.service.ExpenseService;
import expense.calculator.infra.ExpenseRepositoryAdapter;
import expense.calculator.infra.JpaExpenseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

  @Bean
  public ExpenseRepository expenseRepository(JpaExpenseRepository jpaExpenseRepository) {
    return new ExpenseRepositoryAdapter(jpaExpenseRepository);
  }

  @Bean
  public ExpenseService expenseService(ExpenseRepository expenseRepository) {
    return new ExpenseService(expenseRepository);
  }
}
