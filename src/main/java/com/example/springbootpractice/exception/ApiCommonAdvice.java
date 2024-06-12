package com.example.springbootpractice.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiCommonAdvice {

  @ExceptionHandler(ApiErrorException.class)
  public ResponseEntity<ErrorResponse> handleApiErrorException(ApiErrorException e) {
    return ResponseEntity.status(e.getErrorCode().getStatus())
        .body(new ErrorResponse(e.getErrorCode()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    var errorFields = e.getBindingResult().getFieldErrors();
    return new ErrorResponse(ErrorCode.BAD_REQUEST,
        errorFields.stream()
            .map(item -> ErrorField.of(item.getField(), item.getDefaultMessage()))
            .toList()
    );
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse exception(Exception e) {
    return new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
  }
}
