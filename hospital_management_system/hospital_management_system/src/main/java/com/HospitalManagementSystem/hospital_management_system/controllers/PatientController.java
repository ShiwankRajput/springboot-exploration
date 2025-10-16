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

import com.HospitalManagementSystem.hospital_management_system.entities.Patient;
import com.HospitalManagementSystem.hospital_management_system.services.PatientService;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {
	
	private PatientService patientService;
	
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping
	public List<Patient> getAllPatients() {
		return null;
	}
	
	@PostMapping
	public Patient createPatient(@RequestBody Patient patient) {
		return null;
	}
	
	@GetMapping("/{id}")
	public Patient getPatientById(@PathVariable Long id) {
		return null;
	}
	
	@PutMapping("/{id}")
	public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deletePatient(@PathVariable Long id) {
		
	}
	
	
}
