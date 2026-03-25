package com.ShivnexEngineering.journalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ShivnexEngineering.journalApp.entity.User;
import com.ShivnexEngineering.journalApp.repository.JournalEntryRepository;
import com.ShivnexEngineering.journalApp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JournalEntryRepository journalEntryRepository;
		
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public void registerUser(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList("USER"));
			userRepository.save(user);
		}
		catch(Exception e) {
			log.error("Duplicate entry for {}", user.getUserName(), e);
			log.debug("Duplicate entry for {}", user.getUserName(), e);
		}
	}
	
	public void registerAdmin(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER","ADMIN"));
		userRepository.save(user);
	}
	
	public void updateUser(User user){
	    userRepository.save(user);
	}
	
	public void deleteUserByUserName(String userName) {

	    User user = userRepository.findByUserName(userName);

	    if (user != null) {
	        journalEntryRepository.deleteAll(user.getJournalEntries());
	        userRepository.delete(user);
	    }
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserById(ObjectId id) {
		return userRepository.findById(id);
	}
	
	public void deleteById(ObjectId id) {
		userRepository.deleteById(id);
	}
	
	public User findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
}
