package com.capg.sbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.sbs.entity.Login2;

public interface LoginRepository extends JpaRepository<Login2,Integer>{
	
	public Login2 findByUserId(int userId);
	Login2 findByUsername(String username);

}
