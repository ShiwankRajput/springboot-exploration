package com.RideMate.Cab_Booking_System.Entities;

import com.fasterxml.jackson.annotation.JsonFilter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonFilter("RideFilter")
@Entity
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="PickUp location is required")
	private String pickUp;
	
	@NotNull(message="Fare is required")
	@Positive(message="Fare must be greater than 0")
	private Double fare;
	
	@NotBlank(message="Drop location is required")
	private String dropLocation;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@NotNull(message="User is required")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="driver_id")
	@NotNull(message="Driver is required")
	private Driver driver;
	
	public Ride() {
		
	}

	public Ride(Integer id, String pickUp, Double fare, String dropLocation, 
			User user, Driver driver) {
		super();
		this.id = id;
		this.pickUp = pickUp;
		this.fare = fare;
		this.dropLocation = dropLocation;
		this.user = user;
		this.driver = driver;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPickUp() {
		return pickUp;
	}

	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public String getDropLocation() {
		return dropLocation;
	}

	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
}
