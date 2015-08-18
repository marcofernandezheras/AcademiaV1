package model.exceptions;

@SuppressWarnings("serial")
public class ReadException extends Exception {

	public ReadException(String message) {
		super(message);
	}

	public ReadException(Throwable cause) {
		super(cause);
	}

	public ReadException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReadException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
