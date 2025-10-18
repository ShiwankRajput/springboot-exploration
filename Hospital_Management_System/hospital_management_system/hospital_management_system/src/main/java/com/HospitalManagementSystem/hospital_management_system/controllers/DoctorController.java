package com.HospitalManagementSystem.hospital_management_system.controllers;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Page<Doctor> getAllDoctors(@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="2") int size) {
		
		return doctorService.getAllDoctors(page,size);
	}
	
	@PostMapping
	public Doctor createDoctor(@RequestBody Doctor doctor) {
		return doctorService.createDoctor(doctor);
	}
	
	@GetMapping("/{id}")
	public Doctor getDoctorById(@PathVariable Long id) {
		return doctorService.getDoctorById(id);
	}
	
	@PutMapping("/{id}")
	public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
		return doctorService.updateDoctor(id, doctor);
	}
	
	@DeleteMapping("/{id}")
	public void deleteDoctor(@PathVariable Long id) {
		doctorService.deleteDoctor(id);
	}
	
}
