package com.capg.sbs.login.apiresponses;

public class ApiResponse {

	    private int status;
	    private String message;
	    private Object result;
	    private String landingPage;

	    public ApiResponse(int status, String message, Object result, String landingPage){
	        this.status = status;
	        this.message = message;
	        this.result = result;
	        this.landingPage = landingPage;
	    }

	    public int getStatus() {
	        return status;
	    }
	    
	    public String getMessage()
	    {
	    	return message;
	    }
	    
	    public Object getResult(){
	    	return result;
	    }

		public String getLandingPage() {
			return landingPage;
		}
}
