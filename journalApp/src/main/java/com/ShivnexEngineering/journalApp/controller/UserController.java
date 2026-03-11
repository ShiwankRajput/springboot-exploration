package com.ShivnexEngineering.journalApp.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShivnexEngineering.journalApp.entity.User;
import com.ShivnexEngineering.journalApp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PutMapping
	public ResponseEntity<?> updateSpecificUser(@RequestBody User user) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		User existingUser = userService.findUserByUserName(userName);
		
		existingUser.setUserName(user.getUserName());
		existingUser.setPassword(user.getPassword());
		userService.saveUser(existingUser);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> deleteSpecificUser(){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userService.deleteUserByUserName(authentication.getName());
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
}
