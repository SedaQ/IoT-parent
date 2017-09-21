package cz.vutbr.feec.iot.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavel Šeda
 */
public class ServiceLayerException extends DataAccessException {

	private static final long serialVersionUID = 8606352464618478989L;

	public ServiceLayerException(String message) {
		super(message);
	}

	public ServiceLayerException(String message, Throwable cause) {
		super(message, cause);
	}
}
