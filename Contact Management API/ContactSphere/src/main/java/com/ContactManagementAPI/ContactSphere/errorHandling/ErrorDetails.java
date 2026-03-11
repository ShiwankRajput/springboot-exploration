package com.ContactManagementAPI.ContactSphere.errorHandling;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime localTimeStamp;
	private String message;
	private String description;
	
	public ErrorDetails(LocalDateTime localTimeStamp, String message, String description) {
		super();
		this.localTimeStamp = localTimeStamp;
		this.message = message;
		this.description = description;
	}

	public LocalDateTime getLocalTimeStamp() {
		return localTimeStamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}
	
}
