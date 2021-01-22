package com.capg.sbs.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class IncorrectPasswordExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(IncorrectPasswordException.class)
	public ResponseEntity<LoginErrorMessage> toResponse(IncorrectPasswordException e)
	{
		LoginErrorMessage error = new LoginErrorMessage(400, e.getMessage());
		return new ResponseEntity<LoginErrorMessage>(error, HttpStatus.BAD_REQUEST);
	}
}
