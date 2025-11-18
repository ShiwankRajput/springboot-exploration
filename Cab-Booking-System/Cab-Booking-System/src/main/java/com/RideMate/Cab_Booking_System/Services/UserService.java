package com.RideMate.Cab_Booking_System.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.RideMate.Cab_Booking_System.Entities.User;
import com.RideMate.Cab_Booking_System.Repositories.UserRepository;

@Service
public class UserService {

	private UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public void createNewUser(User user) {
		repository.save(user);
	}
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	public Optional<User> getUserById(Integer id) {
		Optional<User> existingUser = repository.findById(id);
		return existingUser;
	}
	
	public void updateExistingUser(User user, Integer id) {
		User existingUser = repository.findById(id).get();
		existingUser.setName(user.getName());
		existingUser.setPhone(user.getPhone());
		existingUser
	}
	
	public void deleteExistingUser(Integer id) {
		repository.deleteById(id);
	}
	
}
