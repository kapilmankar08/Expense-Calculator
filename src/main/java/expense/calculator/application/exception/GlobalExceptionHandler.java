package expense.calculator.application.exception;

import expense.calculator.application.error.ErrorResponse;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
    // Get all validation errors
    BindingResult result = ex.getBindingResult();
    List<String> errorMessages =
        result.getAllErrors().stream()
            .map(
                error -> {
                  if (error instanceof FieldError fieldError) {
                    return fieldError.getField() + ": " + error.getDefaultMessage();
                  }
                  return error.getDefaultMessage();
                })
            .collect(Collectors.toList());

    // Return a custom error response
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("Validation failed", errorMessages));
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<?> handleNoSuchElementException() {
    String errorMessage = "No expense details found";

    ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", List.of(errorMessage));

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<?> handleMissingParameter() {
    String errorMessage = "UUID is required but missing in the request.";

    ErrorResponse errorResponse = new ErrorResponse("Bad Request", List.of(errorMessage));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handleEntityNotFound() {
    String errorMessage = "Expense not found";

    ErrorResponse errorResponse = new ErrorResponse("Bad Request", List.of(errorMessage));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
}
