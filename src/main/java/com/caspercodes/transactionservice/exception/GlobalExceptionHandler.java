package com.caspercodes.transactionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Handle TransactionNotFoundException
  @ExceptionHandler(TransactionNotFoundException.class)
  public ResponseEntity<Object> handleTransactionNotFoundException(TransactionNotFoundException ex) {
    return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  // Handle InvalidCategoryException
  @ExceptionHandler(InvalidCategoryException.class)
  public ResponseEntity<Object> handleInvalidCategoryException(InvalidCategoryException ex) {
    return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  // Handle IllegalArgumentException (e.g., invalid category input)
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
    return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  // Handle generic exceptions (unexpected issues)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGenericException(Exception ex) {
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
  }

  // Standard error response builder
  private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String message) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("message", message);
    body.put("status", status.value());
    body.put("timestamp", LocalDateTime.now());
    body.put("error", status.getReasonPhrase());

    return new ResponseEntity<>(body, status);
  }
}