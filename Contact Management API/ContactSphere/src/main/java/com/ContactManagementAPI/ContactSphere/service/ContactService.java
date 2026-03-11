package com.ContactManagementAPI.ContactSphere.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ContactManagementAPI.ContactSphere.entities.Contact;
import com.ContactManagementAPI.ContactSphere.errorHandling.ContactNotFoundException;
import com.ContactManagementAPI.ContactSphere.repository.ContactRepository;

@Service
public class ContactService {

	private ContactRepository repository;
	
	public ContactService(ContactRepository repository) {
		this.repository = repository;
	}
	
	public List<Contact> getAllContacts(){
		return repository.findAll();
	}
	
	public void createSpecificContact(Contact contact) {
		
		boolean exist = repository.existsByNameAndEmailAndPhoneAndAddress(contact.getName(),
				contact.getEmail(), contact.getPhone(), contact.getAddress());
		
		if(exist) {
			throw new RuntimeException("Contact already Exists");
		}
		
		repository.save(contact);
	}
	
	public Contact getSpecifiedContactById(Integer id) {
		Optional<Contact> contact = repository.findById(id);
		
		if(contact.isEmpty()) {
			throw new ContactNotFoundException("Cannot Found Contact by id -> " + id);
		}
		
		return contact.get();
	}
	
	public void UpdateSpecifiedContactById(Integer id, Contact contact) {
		Contact existingContact = repository.findById(id).get();
		existingContact.setName(contact.getName());
		existingContact.setEmail(contact.getEmail());
		existingContact.setPhone(contact.getPhone());
		existingContact.setAddress(contact.getAddress());
		repository.save(existingContact);
	}
	
	public void deleteSpecifiedContactById(Integer id) {
		repository.deleteById(id);
	}
	
}
