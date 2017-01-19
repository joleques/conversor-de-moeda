package conversor.exception;

public class ParametroInvalidoException extends ConverterException {

	public ParametroInvalidoException() {
		super();
	}

	public ParametroInvalidoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParametroInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParametroInvalidoException(String message) {
		super(message);
	}

	public ParametroInvalidoException(Throwable cause) {
		super(cause);
	}

	
}
