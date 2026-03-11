package com.ContactManagementAPI.ContactSphere.errorHandling;

public class ContactNotFoundException extends RuntimeException{

	public ContactNotFoundException(String message) {
		super(message);
	}
	
}
