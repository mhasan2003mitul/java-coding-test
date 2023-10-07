package com.codingtest.handler;

import com.codingtest.exception.CustomerCurrentAccountAlreadyExistsException;
import com.codingtest.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CodingTestExceptionHandler {
  @ExceptionHandler({CustomerCurrentAccountAlreadyExistsException.class})
  public ResponseEntity<String> handleAlreadyExistsException(CustomerCurrentAccountAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
  }

  @ExceptionHandler({CustomerNotFoundException.class})
  public ResponseEntity<String> handleNotFoundException(CustomerNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<String> handleAllException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }

}
