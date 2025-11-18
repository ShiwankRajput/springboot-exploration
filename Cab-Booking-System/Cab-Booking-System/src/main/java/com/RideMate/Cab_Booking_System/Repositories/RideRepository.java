package com.RideMate.Cab_Booking_System.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RideMate.Cab_Booking_System.Entities.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer>{

	/*
	
	 Super useful for endpoints like:

		GET /v1/rides/user/{userId}
		GET /v1/rides/driver/{driverId}
	 
	*/
	
	List<Ride> findByUserId(Integer userId);
	
	List<Ride> findByDriverId(Integer driverId);
	
}
