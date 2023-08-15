package med.voll.api.infra.exception;

import org.springframework.validation.FieldError;

public record ValidationException (String field, String message) {
  public ValidationException(FieldError error) {
    this(error.getField(), error.getDefaultMessage());
  }
}
