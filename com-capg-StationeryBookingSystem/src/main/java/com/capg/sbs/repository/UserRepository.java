package com.capg.sbs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.sbs.entity.Login2;
import com.capg.sbs.entity.User;




@Repository
public interface UserRepository extends JpaRepository<User, Long> 
{
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);
  
  public User findById(long id);
}