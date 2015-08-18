package model.exceptions.crud;

@SuppressWarnings("serial")
public class RetrieveException extends Exception {

	public RetrieveException(String message) {
		super(message);
	}

	public RetrieveException(Throwable cause) {
		super(cause);
	}

	public RetrieveException(String message, Throwable cause) {
		super(message, cause);
	}

	public RetrieveException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
