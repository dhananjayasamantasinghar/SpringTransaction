package com.h2.exception;

public class AppGenericException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AppGenericException(String message) {
		super(message);
	}
}
