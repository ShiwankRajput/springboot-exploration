package com.RideMate.Cab_Booking_System.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.RideMate.Cab_Booking_System.Controllers.UserController.UserNotFoundException;
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
	
	public User getUserById(Integer id) {
		Optional<User> existingUser = repository.findById(id);
		
		if(existingUser.isEmpty()) {
			throw new UserNotFoundException("User not found by id -> " + id);
		}
		
		return existingUser.get();
	}
	
	public void updateExistingUser(User user, Integer id) {
		User existingUser = repository.findById(id)
				.orElseThrow(()-> new UserNotFoundException("User not Found by id -> " + id));
				
		existingUser.setName(user.getName());
		existingUser.setPhone(user.getPhone());
		
		repository.save(existingUser);
	}
	
	public void deleteExistingUser(Integer id) {
		
		Optional<User> existingUser = repository.findById(id);
		
		if(existingUser.isEmpty()) {
			throw new UserNotFoundException("user not found by id -> " + id);
		}
		
		repository.deleteById(id);
	}
	
}
