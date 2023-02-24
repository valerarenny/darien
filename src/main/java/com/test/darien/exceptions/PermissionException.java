package com.test.darien.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class PermissionException extends RuntimeException {

  private int code;
  private int id;

  public PermissionException(String message) {
    super(message);
  }

  public PermissionException(String message, int code) {
    super(message);
    setCode(code);
  }

  public PermissionException(String message, int code, int id) {
    super(message);
    setCode(code);
    setId(id);
  }

  private static final long serialVersionUID = -388634037813510487L;

}