package com.HospitalManagementSystem.hospital_management_system.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.HospitalManagementSystem.hospital_management_system.entities.Doctor;
import com.HospitalManagementSystem.hospital_management_system.repositories.DoctorRepository;

@Service
public class DoctorService {
	
	private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
	
	private DoctorRepository doctorRepository;
	
	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	public Page<Doctor> getAllDoctors(int page, int size){
		try {
			Pageable pageable = PageRequest.of(page, size);
			return doctorRepository.findAll(pageable);
		}
		catch(Exception e) {
			logger.error("Cannot find all Doctors -> {}", e.getMessage());
			return null;
		}
	}
	
	public Doctor createDoctor(Doctor patient) {
		try {
			return doctorRepository.save(patient);
		}
		catch(Exception e) {
			logger.error("Cannot create a new Doctor -> {}", e.getMessage());
			return null;
		}
	}
	
	public Doctor getDoctorById(Long id) {
		try {
			return doctorRepository.findById(id).get();
		}
		catch(Exception e) {
			logger.error("Cannot get a Doctor with id-{} -> {}", id, e.getMessage());
			return null;
		}
	}
	
	public Doctor updateDoctor(Long id, Doctor doctor) {
		try {
			Optional<Doctor> optional = doctorRepository.findById(id);
			if(optional.isPresent()) {
				Doctor existingDoctor = optional.get();
				existingDoctor.setName(doctor.getName());
				existingDoctor.setSpeciality(doctor.getSpeciality());
				doctorRepository.save(existingDoctor);
			}
			return doctor;
		}
		catch(Exception e) {
			logger.error("Cannot update an existing Doctor with id-{} = {}", id, e.getMessage());
			return null;
		}
	}
	
	public void deleteDoctor(Long id) {
		try {
			doctorRepository.deleteById(id);
		}
		catch(Exception e) {
			logger.error("Cannot delete Doctor with id-{} = {}", id, e.getMessage());
		}
	}
	
}
