package com.ContactManagementAPI.ContactSphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ContactManagementAPI.ContactSphere.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{

	boolean existsByNameAndEmailAndPhoneAndAddress(String name, String email, String phone,
			String address);
	
}
