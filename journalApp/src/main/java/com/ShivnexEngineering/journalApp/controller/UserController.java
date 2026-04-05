package com.ShivnexEngineering.journalApp.controller;


import com.ShivnexEngineering.journalApp.externalApi.response.WeatherResponse;
import com.ShivnexEngineering.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Autowired
	private WeatherService weatherService;
	
	@PutMapping
	public ResponseEntity<?> updateSpecificUser(@RequestBody User user) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		User existingUser = userService.findUserByUserName(userName);
		
		existingUser.setUserName(user.getUserName());
		existingUser.setPassword(user.getPassword());
		userService.registerUser(existingUser);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> deleteSpecificUser(){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userService.deleteUserByUserName(authentication.getName());
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

	@GetMapping
	public ResponseEntity<?> greeting(){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		WeatherResponse weatherResponse = weatherService.weatherReport("Najibabad");

		String message = "Hi " + authentication.getName();

		if(weatherResponse != null){
			message = message + " | Weather feels like : " + weatherResponse.getCurrent().getFeelslike();
			return new ResponseEntity<>(message, HttpStatus.OK);
		}

		return new ResponseEntity<>(message, HttpStatus.OK);

	}
	
}
