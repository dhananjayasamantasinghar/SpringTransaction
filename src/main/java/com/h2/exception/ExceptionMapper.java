package com.h2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapper {

	@ExceptionHandler(value = AppGenericException.class)
	public ResponseEntity<String> handleException(AppGenericException ex) {
		System.out.println("Transaction Rollbacked");
		return new ResponseEntity<String>("App Exception Occured", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		return new ResponseEntity<String>("Exception Occured"+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
