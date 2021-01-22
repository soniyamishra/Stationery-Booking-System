package com.capg.sbs.exceptionhandling;


public class UserNotFoundException extends RuntimeException{
	String message;
	private static final long serialVersionUID = 1L;
	 public UserNotFoundException(String message)
	 {
		 super(message);
	 }


}
