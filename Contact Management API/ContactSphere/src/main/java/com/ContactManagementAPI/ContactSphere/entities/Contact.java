package com.ContactManagementAPI.ContactSphere.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="Name Should not be null")
	@Size(min=2, max=50, message="Name must be between 2 and 50 characters")
	@JsonProperty("Contact_Name")
	private String name;
	
	@Pattern(
		    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
		    message = "Email must be in format example@gmail.com"
		)
	@NotBlank(message="Email cannot be null")
	@JsonProperty("Contact_Email")
	@JsonIgnore
	private String email;
	
	@NotNull(message="Phone number cannot be null")
	@Size(min=10, max=10, message="Phone number should be of 10 digits")
	@JsonProperty("Contact_PhoneNumber")
	private String phone;
	
	@NotNull(message="Address Cannot be null")
	@Size(min=10, max=100, message="Address Should be between 10 to 100 characters")
	@JsonProperty("Contact_Address")
	private String address;
	
	public Contact() {
		
	}
	
	public Contact(Integer id, String name, String email, String phone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
