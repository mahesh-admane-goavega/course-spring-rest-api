package com.springrest.springrest.services;

import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.UserRepo;
import com.springrest.springrest.entities.UserPrincipal;
import com.springrest.springrest.entities.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByUsername(username);
		if(user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		
		return new UserPrincipal(user);
	}
}
