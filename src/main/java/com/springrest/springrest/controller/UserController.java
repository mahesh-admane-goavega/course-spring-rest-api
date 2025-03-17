package com.springrest.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Users;
import com.springrest.springrest.services.UserService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin(origins =  "*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		return userService.verify(user);
	}

	@DeleteMapping(path = "/delete-user/{userId}")
	public Users deleteUser(@PathVariable String userId) {
		return userService.deleteUser(Integer.parseInt(userId));
	}
	
//	@GetMapping("/logout")
//	public String logout(HttpServletRequest request) {
//		request.getSession().invalidate();
//		SecurityContextHolder.clearContext();
//		return "You have been successfully logged out!";
//	}
	

	
}
