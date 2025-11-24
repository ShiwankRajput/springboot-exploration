package com.RideMate.Cab_Booking_System.ExceptionHandling;

public class RideNotFoundException extends RuntimeException{

	public RideNotFoundException(String message) {
		super(message);
	}
	
}
