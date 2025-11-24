package com.RideMate.Cab_Booking_System.Controllers.RideController;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.RideMate.Cab_Booking_System.Controllers.DriverController.DriverController;
import com.RideMate.Cab_Booking_System.Controllers.UserController.UserControllerV1;
import com.RideMate.Cab_Booking_System.Entities.Ride;
import com.RideMate.Cab_Booking_System.Services.RideService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/rides")
public class RideController {

	private final RideService service;

	public RideController(RideService service) {
		this.service = service;
	}

	@GetMapping
	public MappingJacksonValue getAllRides() {
		List<Ride> rides = service.getAllRides();
		return applyRideFilters(rides);
	}

	@GetMapping("/{id}")
	public MappingJacksonValue getRideById(@PathVariable Integer id) {
		Ride ride = service.getSpecifiedRide(id);
		
		EntityModel<Ride> resource = EntityModel.of(ride);
		
		resource.add(linkTo(methodOn(RideController.class).getRideById(id)).withSelfRel());
		resource.add(linkTo(methodOn(RideController.class).getAllRides()).withRel("all-rides"));
		resource.add(linkTo(methodOn(UserControllerV1.class).getUserById(ride.getUser().getId())).withRel("user-details"));
		resource.add(linkTo(methodOn(DriverController.class).getDriverById(ride.getDriver().getId())).withRel("driver-details"));
		
		return applyRideFilters(resource);
	}

	@PostMapping
	public ResponseEntity<Ride> createNewRide(@Valid @RequestBody Ride ride) {
		service.addNewRide(ride);

		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("v1/rides/{id}")
				.buildAndExpand(ride.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public String updateRideById(@Valid @RequestBody Ride ride, @PathVariable Integer id) {
		service.updateExistingRide(ride, id);
		return "Existing Ride updated successfully";
	}

	@DeleteMapping("/{id}")
	public String deleteRideById(@PathVariable Integer id) {
		service.deleteSpecifiedRide(id);
		return "Ride deleted successfully";
	}

	@GetMapping("/user/{userId}")
	public MappingJacksonValue getRidesByUserId(@PathVariable Integer userId) {
		List<Ride> rides = service.getRidesByUserId(userId);
		return applyRideFilters(rides);
	}

	@GetMapping("/driver/{driverId}")
	public MappingJacksonValue getRidesByDriverId(@PathVariable Integer driverId) {
		List<Ride> rides = service.getRidesByDriverId(driverId);
		return applyRideFilters(rides);
	}

	public MappingJacksonValue applyRideFilters(Object data) {

		SimpleFilterProvider filter = new SimpleFilterProvider()
				.addFilter("RideFilter", SimpleBeanPropertyFilter.serializeAll())
				.addFilter("UserFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id","name"))
				.addFilter("DriverFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id","name"));

		MappingJacksonValue wrapper = new MappingJacksonValue(data);
		wrapper.setFilters(filter);

		return wrapper;
	}

}
