package com.springrest.springrest.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Collections;

import org.aspectj.apache.bcel.classfile.Module.Uses;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserPrincipal implements UserDetails
{
	private Users users;
	public UserPrincipal(Users users) {
		this.users = users;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		return users.getPassword();
	}	

	@Override
	public String getUsername() {
		return users.getUsername();
	}

	
}
