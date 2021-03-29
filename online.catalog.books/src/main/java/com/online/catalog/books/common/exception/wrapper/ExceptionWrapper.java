package com.online.catalog.books.common.exception.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionWrapper {

  private final int statusCode;
  private String message;
  private List<String> errors;

  public ExceptionWrapper(HttpStatus status, String message) {
    this.statusCode = status.value();
    this.message = message;
  }

  public ExceptionWrapper(HttpStatus status, List<String> errors) {
    this.statusCode = status.value();
    this.errors = errors;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getMessage() {
    return message;
  }

  public List<String> getErrors() {
    return errors;
  }
}
