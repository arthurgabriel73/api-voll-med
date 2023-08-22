package med.voll.api.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import med.voll.api.domain.DomainException;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandlers {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handle404() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handle400(MethodArgumentNotValidException exception) {
    var errors = exception.getFieldErrors();
    return ResponseEntity.badRequest().body(errors.stream().map(ValidationException::new).toList());
  }

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<Object> handleDomainError(DomainException exception) {
    return ResponseEntity.badRequest().body(exception.getMessage());
  }

  
}
