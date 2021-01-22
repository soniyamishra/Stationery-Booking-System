package com.capg.sbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.sbs.entity.Login;
import com.capg.sbs.exceptionhandling.IncorrectPasswordException;
import com.capg.sbs.exceptionhandling.UserNotFoundException;
import com.capg.sbs.login.apiresponses.ApiResponse;
import com.capg.sbs.login.apiresponses.LogoutResponse;
import com.capg.sbs.repository.LoginRepository;


@Transactional
@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginrepository;
	
	public ApiResponse login(Login login) {
        Login user = loginrepository.findByUsername(login.getUsername());
        
        if(user == null) {
        	System.out.println("Login failed. User not found or username is null. Please re-enter.");
        	throw new UserNotFoundException("User does not exist or username is null");
        }
        String role = user.getRole();
        String landingPage="";
        if(!user.getPassword().equals(login.getPassword())){
        	System.out.println("Login failed. Incorrect password. Please re-enter.");
            throw new IncorrectPasswordException("Password mismatch.");
        }
        else if(user.getPassword().equals(login.getPassword()) && role.equalsIgnoreCase("user"))
        {
        	System.out.println("Login successful for user");
        	landingPage = "http://localhost:2211/users/customer";
        //	return new ApiResponse(200, "Login success", user,landingPage) ;
        }
        
        else if(user.getPassword().equals(login.getPassword()) && role.equalsIgnoreCase("admin"))
        {
        	System.out.println("Login successful for admin");
        	landingPage = "http://localhost:2211/users/admin";
        }
        
        else if(user.getPassword().equals(login.getPassword()) && role.equalsIgnoreCase("manager"))
        {
        	System.out.println("Login successful for manager");
        	landingPage = "http://localhost:2211/users/manager";
        }
        //System.out.println("Login successful");
        return new ApiResponse(200, "Login success", user, landingPage) ;
     
    }
	
	public LogoutResponse logout() {
		System.out.println("logged out successfully");
		return new LogoutResponse(200, "logged out successfully", null);
	}

}
