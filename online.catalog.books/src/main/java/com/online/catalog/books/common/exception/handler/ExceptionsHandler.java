package com.online.catalog.books.common.exception.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.online.catalog.books.common.exception.NotFoundException;
import com.online.catalog.books.common.exception.wrapper.ExceptionWrapper;
import org.hibernate.NonUniqueObjectException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionsHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public ExceptionWrapper handleNotFoundException(NotFoundException exception) {
    return new ExceptionWrapper(NOT_FOUND, exception.getMessage());
  }

  @ExceptionHandler(NonUniqueObjectException.class)
  @ResponseStatus(NOT_FOUND)
  public ExceptionWrapper handleNonUniqueObjectException(NonUniqueObjectException exception) {
    return new ExceptionWrapper(NOT_FOUND, exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(BAD_REQUEST)
  public ExceptionWrapper handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    return new ExceptionWrapper(
        BAD_REQUEST, parseErrors(exception.getBindingResult().getAllErrors()));
  }

  @ExceptionHandler(NumberFormatException.class)
  @ResponseStatus(BAD_REQUEST)
  public ExceptionWrapper handleNumberFormatException(NumberFormatException exception) {
    return new ExceptionWrapper(BAD_REQUEST, exception.getMessage() + " invalid id");
  }

  @ExceptionHandler(JsonParseException.class)
  @ResponseStatus(BAD_REQUEST)
  public ExceptionWrapper handleJsonParseException(JsonParseException exception) {
    return new ExceptionWrapper(BAD_REQUEST, exception.getMessage());
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseStatus(UNSUPPORTED_MEDIA_TYPE)
  public ExceptionWrapper handleHttpMediaTypeNotSupportedException(
      HttpMediaTypeNotSupportedException exception) {
    return new ExceptionWrapper(UNSUPPORTED_MEDIA_TYPE, exception.getMessage());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(METHOD_NOT_ALLOWED)
  public ExceptionWrapper handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException exception) {
    return new ExceptionWrapper(METHOD_NOT_ALLOWED, exception.getMessage());
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public ExceptionWrapper handleNoHandlerFoundException(NoHandlerFoundException exception) {
    return new ExceptionWrapper(NOT_FOUND, exception.getMessage());
  }

  private List<String> parseErrors(List<ObjectError> errors) {
    return errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(toList());
  }
}
