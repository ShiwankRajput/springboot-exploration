package com.ContactManagementAPI.ContactSphere.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ContactManagementAPI.ContactSphere.entities.Contact;
import com.ContactManagementAPI.ContactSphere.service.ContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/contacts")
public class ContactController {

	private ContactService service;
	
	public ContactController(ContactService service) {
		this.service = service;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	@GetMapping(produces={"application/json","application/xml"})
	public List<Contact> getAllContact(){
		logger.info("Fetched all contacts from Server");
		logger.warn("Getting warned while fetching contacts from Server");
		logger.error("Getting error while fetching contacts from Server");
		return service.getAllContacts();
	}
	
	@PostMapping
	public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
		service.createSpecificContact(contact);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("{id}")
				.buildAndExpand(contact.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("{id}")
	public EntityModel<Contact> getContactById(@PathVariable Integer id) {
		Contact contact = service.getSpecifiedContactById(id);
		
		EntityModel<Contact> model = EntityModel.of(contact);
		
		model.add(linkTo(methodOn(ContactController.class).getAllContact()).
				withRel("all-contacts"));
		
		return model;
	}
	
	@DeleteMapping("{id}")
	public String deleteById(@PathVariable Integer id) {
		service.deleteSpecifiedContactById(id);
		return "Contact Deleted Successfully";
	}
	
	@PutMapping("{id}")
	public String updateById(@PathVariable Integer id, @Valid @RequestBody Contact contact) {
		service.UpdateSpecifiedContactById(id, contact);
		return "Contact Updated successfully";
	}
	
}
