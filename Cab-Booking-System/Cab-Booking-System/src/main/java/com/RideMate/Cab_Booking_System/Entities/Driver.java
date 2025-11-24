package com.RideMate.Cab_Booking_System.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonFilter("DriverFilter")
@Entity
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min=2, message="Name must contains atleast 2 characters")
	private String name;
	
	@NotNull
	@DecimalMin("1.0")
	@DecimalMax("5.0")
	private Double rating;
	
	@OneToMany(mappedBy="driver", cascade=CascadeType.ALL)
	private List<Ride> rides;
	
	public Driver() { 
		
	}
	
	public Driver(Integer id, String name, Double rating) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getRating() {
		return rating;
	}
	
	public void setRating(Double rating) {
		this.rating = rating;
	}

	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}
	
}
