package com.RideMate.Cab_Booking_System.ExceptionHandling;

public class DriverNotFoundException extends RuntimeException{

	public DriverNotFoundException(String message) {
		super(message);
	}
	
}
