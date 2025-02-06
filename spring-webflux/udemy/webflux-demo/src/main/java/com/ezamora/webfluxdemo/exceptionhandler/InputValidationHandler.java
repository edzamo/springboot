package com.ezamora.webfluxdemo.exceptionhandler;

import com.ezamora.webfluxdemo.dto.InputFailedValidationResponse;
import com.ezamora.webfluxdemo.exception.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

  @ExceptionHandler(InputValidationException.class)
  public ResponseEntity<InputFailedValidationResponse> handleException(
      InputValidationException ex) {
    InputFailedValidationResponse response = new InputFailedValidationResponse();
    response.setInput(ex.getInput());
    response.setErrorCode(ex.getErrorCode());
    response.setMessage(ex.getMessage());
    return ResponseEntity.badRequest().body(response);
  }
}
