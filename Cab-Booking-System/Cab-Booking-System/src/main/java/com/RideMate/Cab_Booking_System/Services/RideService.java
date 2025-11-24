package com.RideMate.Cab_Booking_System.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.RideMate.Cab_Booking_System.Controllers.UserController.UserNotFoundException;
import com.RideMate.Cab_Booking_System.Entities.Driver;
import com.RideMate.Cab_Booking_System.Entities.Ride;
import com.RideMate.Cab_Booking_System.Entities.User;
import com.RideMate.Cab_Booking_System.ExceptionHandling.DriverNotFoundException;
import com.RideMate.Cab_Booking_System.ExceptionHandling.RideNotFoundException;
import com.RideMate.Cab_Booking_System.Repositories.DriverRepository;
import com.RideMate.Cab_Booking_System.Repositories.RideRepository;
import com.RideMate.Cab_Booking_System.Repositories.UserRepository;

@Service
public class RideService {

	private RideRepository repository;
	
	private UserRepository userRepository;
	
	private DriverRepository driverRepository;
	
	public RideService(RideRepository repository) {
		this.repository = repository;
	}
	
	
	public List<Ride> getAllRides(){
		return repository.findAll();
	}
	
	public void addNewRide(Ride ride) {
		repository.save(ride);
	}
	
	public Ride getSpecifiedRide(Integer id) {
		
		Optional<Ride> existingRide = repository.findById(id);
		
		if(existingRide.isEmpty()) {
			throw new RideNotFoundException("Cannot find Ride by id -> " + id);
		}
		
		return existingRide.get();
		
	}
	
	public void updateExistingRide(Ride ride, Integer id) {
		
		Ride existingRide = repository.findById(id)
				.orElseThrow(
						() -> new RideNotFoundException("Cannot found Ride by id -> " + id));
		
		existingRide.setPickUp(ride.getPickUp());
		existingRide.setDropLocation(ride.getDropLocation());
		existingRide.setFare(ride.getFare());
		existingRide.setUser(ride.getUser());
		existingRide.setDriver(ride.getDriver());
		
		repository.save(existingRide);
		
	}
	
	public void deleteSpecifiedRide(Integer id) {
		
		Optional<Ride> existingRide = repository.findById(id);
		
		if(existingRide.isEmpty()) {
			throw new RideNotFoundException("Cannot found Ride by id -> " + id);
		}
		
		repository.deleteById(id);
	}
	
	public List<Ride> getRidesByUserId(Integer id){
		
		Optional<User> existingUser = userRepository.findById(id);
		
		if(existingUser.isEmpty()) {
			throw new UserNotFoundException("User not found by id -> " + id);
		}
		
		return repository.findByUserId(id);
	}
	
	public List<Ride> getRidesByDriverId(Integer id){
		
		Optional<Driver> existingDriver = driverRepository.findById(id);
		
		if(existingDriver.isEmpty()) {
			throw new DriverNotFoundException("Cannot found Driver by id -> " + id);
		}
		
		return repository.findByDriverId(id);
	}
	
}
