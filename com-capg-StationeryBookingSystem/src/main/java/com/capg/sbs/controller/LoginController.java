package com.capg.sbs.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.sbs.entity.Login;
import com.capg.sbs.login.apiresponses.ApiResponse;
import com.capg.sbs.login.apiresponses.LogoutResponse;
import com.capg.sbs.service.LoginService;

@CrossOrigin
@RestController
//@RequestMapping("/users")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("users/login")
    public ApiResponse login(@RequestBody Login loginDto){
        return loginService.login(loginDto);
    }
    
    @GetMapping("users/admin")
    public String adminPage() {
    	return "Admin Page";
    }
    
    @GetMapping("users/manager")
    public String managerPage() {
    	return "Manager Page";
    }
    
    @PostMapping("users/logout")
    public LogoutResponse logout() throws InterruptedException
    {
    	return loginService.logout();
    }
    
    @GetMapping("/loggedout")
    public String exit() {
    	return "You have been logged out";
    }
}

