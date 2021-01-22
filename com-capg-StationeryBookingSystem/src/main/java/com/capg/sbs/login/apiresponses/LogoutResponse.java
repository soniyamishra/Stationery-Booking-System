package com.capg.sbs.login.apiresponses;

public class LogoutResponse {
	private int status;
    private String message;
    private Object result;
    
    public LogoutResponse() {}
    public LogoutResponse(int status, String message, Object result)
    {
    	this.setStatus(status);
    	this.setMessage(message);
    	this.setResult(result);
    }
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
