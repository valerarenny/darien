package com.test.darien.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {

  private int code;
  private int id;

  public NoContentException(String message) {
    super(message);
  }

  public NoContentException(String message, int code) {
    super(message);
    setCode(code);
  }

    public NoContentException(String message, int code, int id) {
        super(message);
        setCode(code);
        setId(id);
    }

  private static final long serialVersionUID = 6653722042026450282L;

}