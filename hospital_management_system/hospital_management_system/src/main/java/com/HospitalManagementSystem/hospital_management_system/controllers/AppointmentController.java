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

import com.HospitalManagementSystem.hospital_management_system.entities.Appointment;
import com.HospitalManagementSystem.hospital_management_system.services.AppointmentService;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

	private AppointmentService appointmentService;
	
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@GetMapping
	public List<Appointment> getAllAppointments() {
		return null;
	}
	
	@PostMapping
	public Appointment createAppointment(@RequestBody Appointment appointment) {
		return null;
	}
	
	@GetMapping("/{id}")
	public Appointment getAppointmentById(@PathVariable Long id) {
		return null;
	}
	
	@PutMapping("/{id}")
	public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteAppointment(@PathVariable Long id) {
		
	}
	
}
