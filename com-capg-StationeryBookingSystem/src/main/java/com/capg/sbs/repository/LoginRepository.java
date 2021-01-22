package com.capg.sbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.sbs.entity.Login;

public interface LoginRepository extends JpaRepository<Login,Integer>{
	
	public Login findByUserId(int userId);
	Login findByUsername(String username);

}
