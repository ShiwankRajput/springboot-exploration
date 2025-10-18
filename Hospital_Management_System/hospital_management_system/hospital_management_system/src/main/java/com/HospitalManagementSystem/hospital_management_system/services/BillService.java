package com.HospitalManagementSystem.hospital_management_system.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.HospitalManagementSystem.hospital_management_system.entities.Bill;
import com.HospitalManagementSystem.hospital_management_system.repositories.BillRepository;

@Service
public class BillService {
	
	private static final Logger logger = LoggerFactory.getLogger(BillService.class);
	
	private BillRepository billRepository;
	
	public BillService(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	public Page<Bill> getAllBills(int page, int size){
		try {
			Pageable pageable = PageRequest.of(page, size);
			return billRepository.findAll(pageable);
		}
		catch(Exception e) {
			logger.error("Cannot get All Bills = {}", e.getMessage());
			return null;
		}
	}
	
	public Bill createBill(Bill patient) {
		try {
			return billRepository.save(patient);
		}
		catch(Exception e) {
			logger.error("Cannot create Patient = {}", e.getMessage());
			return null;
		}
	}
	
	public Bill getBillById(Long id) {
		try {
			return billRepository.findById(id).get();
		}
		catch(Exception e) {
			logger.error("Cannot get Patient with id-{} = {}", id, e.getMessage());
			return null;
		}
	}
	
	public Bill updateBill(Long id, Bill bill) {
		try {
			Optional<Bill> optional = billRepository.findById(id);
			if(optional.isPresent()) {
				Bill existingBill = optional.get();
				existingBill.setPatient_id(bill.getPatient_id());
				existingBill.setAmount(bill.getAmount());
				existingBill.setStatus(bill.getStatus());
				billRepository.save(existingBill);
			}
			return bill;
		}
		catch(Exception e) {
			logger.error("Cannot update Patient with id-{} = {}", id, e.getMessage());
			return null;
		}
	}
	
	public void deleteBill(Long id) {
		try {
			billRepository.deleteById(id);
		}
		catch(Exception e) {
			logger.error("Cannot delete Patient by id-{} = {}", id, e.getMessage());
		}
	}
	
}
