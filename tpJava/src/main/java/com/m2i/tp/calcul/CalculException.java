package com.m2i.tp.calcul;

public class CalculException extends RuntimeException {

	public CalculException() {
		
	}

	public CalculException(String message) {
		super(message);
		
	}

	public CalculException(Throwable cause) {
		super(cause);
	
	}

	public CalculException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public CalculException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

}
