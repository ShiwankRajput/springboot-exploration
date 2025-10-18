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
	public Page<Patient> getAllPatients(@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="2") int size) {
		
		return patientService.getAllPatients(page,size);
	}
	
	@PostMapping
	public Patient createPatient(@RequestBody Patient patient) {
		return patientService.createPatient(patient);
	}
	
	@GetMapping("/{id}")
	public Patient getPatientById(@PathVariable Long id) {
		return patientService.getPatientById(id);
	}
	
	@PutMapping("/{id}")
	public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
		return patientService.updatePatient(id,patient);
	}
	
	@DeleteMapping("/{id}")
	public void deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
	}
	
	
}
