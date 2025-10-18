package com.HospitalManagementSystem.hospital_management_system.controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.HospitalManagementSystem.hospital_management_system.entities.Appointment;
import com.HospitalManagementSystem.hospital_management_system.services.AppointmentService;
import com.HospitalManagementSystem.hospital_management_system.services.WebHookService;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

	private AppointmentService appointmentService;
	
	@Autowired
	private WebHookService webHookService;
	
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@GetMapping
	public Page<Appointment> getAllAppointments(@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="2") int size) {
		
		return appointmentService.getAllAppointments(page, size);
	}
	
	@PostMapping
	public Appointment createAppointment(@RequestBody Appointment appointment) {
		
		Appointment returnedAppointment =appointmentService.createAppointment(appointment);
		
		//prepare the webHook payLoad
		Map<String, Object> payload = new HashMap<>();
		
		payload.put("appointmentID",appointment.getId());
		payload.put("appointmentPatient_ID",appointment.getPatient_id());
		payload.put("appointmentDoctor_ID",appointment.getDoctor_id());
		payload.put("appointmentDate",appointment.getDate());
		
		// send the webHook
		String webHookurl = "http://localhost:8081/webhook";
		webHookService.sendWebHook(webHookurl, payload);
		
		return returnedAppointment;
	}
	
	@GetMapping("/{id}")
	public Appointment getAppointmentById(@PathVariable Long id) {
		return appointmentService.getAppointmentById(id);
	}
	
	@PutMapping("/{id}")
	public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
		return appointmentService.updateAppointment(id, appointment);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAppointment(@PathVariable Long id) {
		appointmentService.deleteAppointment(id);
	}
	
}
