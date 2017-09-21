package cz.vutbr.feec.iot.exceptions;

import cz.vutbr.feec.iot.exception.ServiceLayerException;

/**
 * @author Pavel Šeda
 *
 */
public class FacadeLayerException extends ServiceLayerException {

	private static final long serialVersionUID = 6128677841817511516L;

	public FacadeLayerException(String message) {
		super(message);
	}

	public FacadeLayerException(String message, Throwable cause) {
		super(message, cause);
	}

}
