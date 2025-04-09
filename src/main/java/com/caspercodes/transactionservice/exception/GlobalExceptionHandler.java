package com.caspercodes.transactionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice // This annotation is for it to handle exceptions globally for all controllers
public class GlobalExceptionHandler extends RuntimeException {

  // To handle TransactionNotFoundException
  @ExceptionHandler(TransactionNotFoundException.class)
  public ResponseEntity<Object> handleTransactionNotFoundException(TransactionNotFoundException ex) {
    return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  // To handle IllegalArgumentException (such as invalid category)
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
    return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  // To handle all other exceptions that I don't catch
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGenericException(Exception ex) {
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
  }


  // For consistent error responses
  private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String message) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("message", message);
    body.put("status", status.value());
    body.put("timestamp", LocalDateTime.now());
    body.put("error", status.getReasonPhrase());

    return new ResponseEntity<>(body, status);
  }
}