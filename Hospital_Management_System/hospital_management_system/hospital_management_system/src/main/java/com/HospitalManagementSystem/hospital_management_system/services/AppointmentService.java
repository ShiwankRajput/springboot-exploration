package com.HospitalManagementSystem.hospital_management_system.services;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.HospitalManagementSystem.hospital_management_system.entities.Appointment;
import com.HospitalManagementSystem.hospital_management_system.repositories.AppointmentRepository;

@Service
public class AppointmentService {
	
	private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
	
	private AppointmentRepository appointmentRepository;
	
	public AppointmentService(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	public Page<Appointment> getAllAppointments(int page, int size){
		try {
			Pageable pageable = PageRequest.of(page, size);
			return appointmentRepository.findAll(pageable);
		}
		catch(Exception e) {
			logger.error("Cannot get All Appointments = {}", e.getMessage());
			return null;
		}
	}
	
	public Appointment createAppointment(Appointment patient) {
		try {
			return appointmentRepository.save(patient);
		}
		catch(Exception e) {
			logger.error("Cannot create new Appointment = {}", e.getMessage());
			return null;
		}
	}
	
	public Appointment getAppointmentById(Long id) {
		try {
			return appointmentRepository.findById(id).get();
		}
		catch(Exception e) {
			logger.error("Cannot get Appointment for id-{} = {}", id, e.getMessage());
			return null;
		}
	}
	
	public Appointment updateAppointment(Long id, Appointment appointment) {
		try {
			Optional<Appointment> optional = appointmentRepository.findById(id);
			if(optional.isPresent()) {
				Appointment existingAppointment = optional.get();
				existingAppointment.setPatient_id(appointment.getPatient_id());
				existingAppointment.setDoctor_id(appointment.getDoctor_id());
				existingAppointment.setDate(appointment.getDate());
				appointmentRepository.save(existingAppointment);
			}
			return appointment;
		}
		catch(Exception e) {
			logger.error("Cannot update Appointment for id-{} = {}", id, e.getMessage());
			return null;
		}
	}
	
	public void deleteAppointment(Long id) {
		try {
			appointmentRepository.deleteById(id);
		}
		catch(Exception e) {
			logger.error("Cannot delete Appointment for id-{} = {}", id, e.getMessage());
		}
	}
	
}
