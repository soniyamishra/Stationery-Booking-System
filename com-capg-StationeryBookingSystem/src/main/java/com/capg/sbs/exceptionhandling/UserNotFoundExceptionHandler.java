package com.capg.sbs.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class UserNotFoundExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<LoginErrorMessage> toResponse(UserNotFoundException e)
	{
		LoginErrorMessage error = new LoginErrorMessage(404, e.getMessage());
		return new ResponseEntity<LoginErrorMessage>(error, HttpStatus.NOT_FOUND);
	}
	
}
