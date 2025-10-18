package com.HospitalManagementSystem.hospital_management_system.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.HospitalManagementSystem.hospital_management_system.entities.Patient;
import com.HospitalManagementSystem.hospital_management_system.repositories.PatientRepository;

@Service
public class PatientService {
	
	private PatientRepository patientRepository; 
	
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PatientService.class);
	
	public Page<Patient> getAllPatients(int page, int size){
		try {
			Pageable pageable = PageRequest.of(page, size);
			return patientRepository.findAll(pageable);
		}
		catch(Exception e) {
			logger.error("Cannot get All Patient = {}", e.getMessage());
			return null;
		}
	}
	
	public Patient createPatient(Patient patient) {
		try {
			return patientRepository.save(patient);
		}
		catch(Exception e) {
			logger.error("Cannot create new patient = {}", e.getMessage());
			return null;
		}
	}
	
	public Patient getPatientById(Long id) {
		try {
			return patientRepository.findById(id).get();
		}
		catch(Exception e) {
			logger.error("Cannot get the patient by id-{} = {}", id, e.getMessage());
			return null;
		}
	}
	
	public Patient updatePatient(Long id, Patient patient) {
		try {
			Optional<Patient> optional = patientRepository.findById(id);
			if(optional.isPresent()) {
				Patient existingPatient = optional.get();
				existingPatient.setName(patient.getName());
				existingPatient.setGender(patient.getGender());
				existingPatient.setAge(patient.getAge());
				patientRepository.save(existingPatient);
			}
			return patient;
		}
		catch(Exception e) {
			logger.error("Patient with id-{} cannot be found = {}", id, e.getMessage());
			return null;
		}
	}
	
	public void deletePatient(Long id) {
		try {
			patientRepository.deleteById(id);
		}
		catch(Exception e) {
			logger.error("Cannot find Patient by id-{} = {}", id, e.getMessage());
		}
	}
	
}
