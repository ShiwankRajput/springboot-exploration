package com.HospitalManagementSystem.hospital_management_system.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagementSystem.hospital_management_system.entities.Doctor;
import com.HospitalManagementSystem.hospital_management_system.services.DoctorService;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

	private DoctorService doctorService;
	
	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	@GetMapping
	public List<Doctor> getAllDoctors() {
		return null;
	}
	
	@PostMapping
	public Doctor createDoctor(@RequestBody Doctor doctor) {
		return null;
	}
	
	@GetMapping("/{id}")
	public Doctor getDoctorById(@PathVariable Long id) {
		return null;
	}
	
	@PutMapping("/{id}")
	public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteDoctor(@PathVariable Long id) {
		
	}
	
}
