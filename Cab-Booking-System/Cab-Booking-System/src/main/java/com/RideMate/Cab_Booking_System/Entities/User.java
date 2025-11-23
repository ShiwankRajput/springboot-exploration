package com.RideMate.Cab_Booking_System.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonFilter("UserFilter")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 2, message = "Name should contains more than 2 characters")
	private String name;
	
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number must start with 6,7,8,9 and be 10 digits")
	private String phone;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<Ride> rides;
	
	public User() {
		
	}

	public User(Integer id, String name, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}
	
}
