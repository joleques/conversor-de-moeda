package conversor.exception;

public class ConverterException extends Exception {

	public ConverterException() {
		super();
	}

	public ConverterException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConverterException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConverterException(String message) {
		super(message);
	}

	public ConverterException(Throwable cause) {
		super(cause);
	}

}
