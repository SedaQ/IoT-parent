package cz.vutbr.feec.iot.exceptions;

/**
 * @author Pavel Šeda
 *
 */
public class EmailExistsException extends RuntimeException {
	private static final long serialVersionUID = -2778323828935765733L;

	public EmailExistsException(String email) {
		super(String.format("'%s' is already in use", email));
	}
}
