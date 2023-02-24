package com.test.darien.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicado")
public class DataIntegrityViolationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private int code;

  public DataIntegrityViolationException(String message) {
    super(message);
  }

  public DataIntegrityViolationException(String message, int code) {
    super(message);
    setCode(code);
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

}