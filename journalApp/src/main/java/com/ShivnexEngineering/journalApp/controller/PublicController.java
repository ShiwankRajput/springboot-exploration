package com.ShivnexEngineering.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShivnexEngineering.journalApp.entity.User;
import com.ShivnexEngineering.journalApp.service.CustomUserDetailsService;
import com.ShivnexEngineering.journalApp.service.UserService;
import com.ShivnexEngineering.journalApp.utils.JwtUtils;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtil;

	@GetMapping("/health-check")
	public String healthCheck() {
		return "Application Health is OK!";
	}
	
	@PostMapping("/signUp")
	public void signUp(@RequestBody User user) {
		userService.registerUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
			String token = jwtUtil.generateToken(userDetails.getUsername());
			return new ResponseEntity<>(token,HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println("Exception" + e);
			return new ResponseEntity<>("Invalid UserName or Password",HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
