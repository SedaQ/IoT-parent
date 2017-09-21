package cz.vutbr.feec.iot.rest;

import java.util.List;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

/**
 * Represents a possible representation of errors to be used with @ControllerAdvice global exception
 * handler
 * 
 * @author Pavel Šeda
 */
public class ApiError {

  private HttpStatus status;
  private String message;
  private List<String> errors;

  public ApiError() {
    super();
  }

  public ApiError(final HttpStatus status, final String message, final List<String> errors) {
    super();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  public ApiError(final HttpStatus status, final String message, final String error) {
    super();
    this.status = status;
    this.message = message;
    errors = Arrays.asList(error);
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(final HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(final List<String> errors) {
    this.errors = errors;
  }

  public void setError(final String error) {
    errors = Arrays.asList(error);
  }

}
