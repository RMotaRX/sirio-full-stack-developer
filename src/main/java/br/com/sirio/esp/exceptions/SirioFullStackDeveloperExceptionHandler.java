package br.com.sirio.esp.exceptions;

import br.com.sirio.esp.domain.dto.response.FieldErrorResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RequiredArgsConstructor
public class SirioFullStackDeveloperExceptionHandler {

  /*
  * Here, I could add a class with a list of errors and inject the values of the messages
  * from the messages.properties file as constants; however, I ran out of time to refactor.
  * */

  private final MessageSource messageSource;

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex, WebRequest request) {
    String errorMessage = messageSource.getMessage("HttpMessageNotReadableException", null, LocaleContextHolder.getLocale());
    var error = new Error(HttpStatus.BAD_REQUEST.value(), "HttpMessageNotReadableException", errorMessage, null);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler({ MethodArgumentNotValidException.class })
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, WebRequest request) {
    String errorMessage = messageSource.getMessage("MethodArgumentNotValidException", null, LocaleContextHolder.getLocale());
    var error = new Error(
        HttpStatus.UNPROCESSABLE_ENTITY.value(),
        "MethodArgumentNotValidException",
        errorMessage,
        ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new FieldErrorResponse(
                fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "failed",
                fieldError.getField()))
            .collect(Collectors.toList())
    );

    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
  }

  @ExceptionHandler({ SQLIntegrityConstraintViolationException.class })
  public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(
      SQLIntegrityConstraintViolationException ex, WebRequest request) {
    String errorMessage = messageSource.getMessage("CpfAssociatedWithPersonaException", null, LocaleContextHolder.getLocale());
    var error = new Error(HttpStatus.CONFLICT.value(), "CpfAssociatedWithPersonaException", errorMessage, null);

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }
}
