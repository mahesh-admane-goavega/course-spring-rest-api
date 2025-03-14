package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.entities.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>  {
	public Users findByUsername(String username);
}
