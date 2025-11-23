package com.RideMate.Cab_Booking_System.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.RideMate.Cab_Booking_System.Entities.Driver;
import com.RideMate.Cab_Booking_System.ExceptionHandling.DriverNotFoundException;
import com.RideMate.Cab_Booking_System.Repositories.DriverRepository;

@Service
public class DriverService {

	private DriverRepository repository;
	
	public DriverService(DriverRepository repository) {
		this.repository = repository;
	}
	
	
	public List<Driver> getAllDrivers(){
		return repository.findAll();
	}
	
	public void addNewDriver(Driver driver) {
		repository.save(driver);
	}
	
	public Driver getSpecifiedDriver(Integer id){
		Optional<Driver> existingDriver = repository.findById(id);
		
		if(existingDriver.isEmpty()) {
			throw new DriverNotFoundException("Driver not found by id -> " + id);
		}
		
		return existingDriver.get();
	}
	
	public void updateSpecifiedDriver(Driver driver, Integer id) {
		Driver existingDriver = repository.findById(id)
				.orElseThrow(() -> new DriverNotFoundException("Driver Not Found by id -> " + id));
		
		existingDriver.setName(driver.getName());
		existingDriver.setRating(driver.getRating());
		
		repository.save(existingDriver);
	}
	
	public void deleteSpecifiedDriver(Integer id) {
		
		Optional<Driver> driver = repository.findById(id);
		
		if(driver.isEmpty()) {
			throw new DriverNotFoundException("Driver Not Found by id -> " + id);
		}
		
		repository.deleteById(id);
	}
	
}
