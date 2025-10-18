package com.HospitalManagementSystem.hospital_management_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalManagementSystem.hospital_management_system.entities.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
