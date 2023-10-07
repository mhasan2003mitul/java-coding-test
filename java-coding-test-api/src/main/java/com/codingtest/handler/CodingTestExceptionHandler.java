package com.codingtest.handler;

import com.codingtest.exception.AccountNotFoundException;
import com.codingtest.exception.CustomerCurrentAccountAlreadyExistsException;
import com.codingtest.exception.CustomerNotFoundException;
import com.codingtest.exception.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CodingTestExceptionHandler {
  @ExceptionHandler({CustomerCurrentAccountAlreadyExistsException.class})
  public ResponseEntity<ErrorMessage> handleAlreadyExistsException(CustomerCurrentAccountAlreadyExistsException ex) {
    log.debug(ex.getMessage(), ex);
    ErrorMessage errorMessage = ErrorMessage.builder().code(HttpStatus.CONFLICT).message(ex.getMessage()).build();
    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
  }

  @ExceptionHandler({CustomerNotFoundException.class})
  public ResponseEntity<ErrorMessage> handleNotFoundException(CustomerNotFoundException ex) {
    log.debug(ex.getMessage(), ex);
    ErrorMessage errorMessage = ErrorMessage.builder().code(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
  }

  @ExceptionHandler({AccountNotFoundException.class})
  public ResponseEntity<ErrorMessage> handleNotFoundException(AccountNotFoundException ex) {
    log.debug(ex.getMessage(), ex);
    ErrorMessage errorMessage = ErrorMessage.builder().code(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<ErrorMessage> handleAllException(Exception ex) {
    log.debug(ex.getMessage(), ex);
    ErrorMessage errorMessage = ErrorMessage.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage()).build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
  }

}
