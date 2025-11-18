package com.RideMate.Cab_Booking_System.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RideMate.Cab_Booking_System.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
