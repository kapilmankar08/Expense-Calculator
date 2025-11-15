package expense.calculator.application;

import static expense.calculator.application.ExpenseMapper.toResponse;

import expense.calculator.domain.incoming.ExpenseUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses/")
public class ExpenseController {

  private final ExpenseUseCase expense;

  public ExpenseController(ExpenseUseCase expense) {
    this.expense = expense;
  }

  @PostMapping
  public ResponseEntity<ExpenseResponse> addExpense(
      @RequestBody @Valid ExpenseRequest expenseRequest) {
    return ResponseEntity.ok(
        toResponse(
            expense.createExpense(
                expenseRequest.getCategory(),
                expenseRequest.getExpenseToBeAdded(),
                expenseRequest.getAmount())));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public ResponseEntity<ExpenseResponse> getExpense(@RequestParam String uuid) {

    return ResponseEntity.ok(toResponse(expense.getExpense(uuid)));
  }

  @PreAuthorize("hasRole('USER')")
  @DeleteMapping
  public ResponseEntity<String> deleteExpense(@RequestParam String uuid) {
    return ResponseEntity.ok(expense.deleteExpense(uuid));
  }

  @PatchMapping
  public ResponseEntity<ExpenseResponse> updateExpense(
      @RequestBody ExpenseRequest expenseRequest, @RequestParam String uuid) {
    return ResponseEntity.ok(
        toResponse(
            expense.updateExpense(
                expenseRequest.getExpenseToBeAdded(), expenseRequest.getAmount(), uuid)));
  }
}
