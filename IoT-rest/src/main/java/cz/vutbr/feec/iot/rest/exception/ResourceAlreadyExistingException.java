package cz.vutbr.feec.iot.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Pavel Šeda
 *
 */
public class ResourceAlreadyExistingException extends RuntimeException {

  private static final long serialVersionUID = 7366705350330632956L;

  public ResourceAlreadyExistingException() {}

  public ResourceAlreadyExistingException(Exception ex) {}
}
