package com.test.darien.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

  private int code;
  private int id;

  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(String message, int code) {
    super(message);
    setCode(code);
  }

  public ValidationException(String message, int code, int id) {
    super(message);
    setCode(code);
    setId(id);
  }

  private static final long serialVersionUID = -1568466835743411225L;

}