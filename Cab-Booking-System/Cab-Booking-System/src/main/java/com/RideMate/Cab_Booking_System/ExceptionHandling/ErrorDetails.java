package com.RideMate.Cab_Booking_System.ExceptionHandling;

import java.time.LocalDateTime;

public class ErrorDetails {
	
	private LocalDateTime timeStamp;
	private String message;
	private String Description;
	
	public ErrorDetails(LocalDateTime timeStamp, String message, String description) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		Description = description;
	}
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getDescription() {
		return Description;
	}

}
