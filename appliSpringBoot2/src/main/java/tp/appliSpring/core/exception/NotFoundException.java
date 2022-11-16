package tp.appliSpring.core.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND) //404
//NB: if ResponseEntityExceptionHandler/@ControllerAdvice is present , no need of @ResponseStatus
public class NotFoundException extends RuntimeException {

	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
