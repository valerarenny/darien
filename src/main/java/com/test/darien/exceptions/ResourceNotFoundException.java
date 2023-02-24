package com.test.darien.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  private int code;
  private int id;

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, int code) {
    super(message);
    setCode(code);
  }

  public ResourceNotFoundException(String message, int code, int id) {
    super(message);
    setCode(code);
    setId(id);
  }

  private static final long serialVersionUID = 6653722042026450271L;

}