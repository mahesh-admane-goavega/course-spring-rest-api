package com.springrest.springrest.services;

import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.UserRepo;
import com.springrest.springrest.entities.Users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authManager;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public Users register(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return	userRepo.save(user);
	}

	public Users deleteUser(int userId) {
		Optional<Users> userOpt = userRepo.findById(userId);
		Users user = userOpt.get();
		userRepo.delete(user);
		return user;
	}
	
	public String verify(Users user) {
		System.out.println("users" +user);
		Authentication authentication = 
				authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())); 
		if(authentication.isAuthenticated())
			return jwtService.generateToken(user.getUsername());
		
		return "fail";
	}
}
