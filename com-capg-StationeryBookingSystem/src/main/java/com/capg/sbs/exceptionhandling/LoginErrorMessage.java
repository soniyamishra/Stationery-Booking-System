package com.capg.sbs.exceptionhandling;

public class LoginErrorMessage {
     int errorCode;
     String errorMessage;
    
    public LoginErrorMessage() {
    	
    }
	public LoginErrorMessage(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
