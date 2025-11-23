package com.RideMate.Cab_Booking_System.Controllers.DriverController;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.RideMate.Cab_Booking_System.Entities.Driver;
import com.RideMate.Cab_Booking_System.Services.DriverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/drivers")
public class DriverController {

	public DriverService service;
	
	public DriverController(DriverService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Driver> getAllDrivers(){
		return service.getAllDrivers();
	}
	
	@GetMapping("/{id}")
	public Driver getDriverById(@PathVariable Integer id) {
		return service.getSpecifiedDriver(id);
	}
	
	@PostMapping
	public ResponseEntity<Driver> createDriver(@Valid @RequestBody Driver driver) {
		service.addNewDriver(driver);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/v1/drivers/{id}")
				.buildAndExpand(driver.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public String updateExistingUser(@Valid @RequestBody Driver driver, @PathVariable Integer id) {
		service.updateSpecifiedDriver(driver, id);
		return "Existing Driver updated Successfully";
	}
	
	@DeleteMapping("/{id}")
	public String deleteDriverById(@PathVariable Integer id) {
		service.deleteSpecifiedDriver(id);
		return "Driver deleted successfully";
	}
	
}
