package com.HospitalManagementSystem.hospital_management_system.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagementSystem.hospital_management_system.entities.Bill;
import com.HospitalManagementSystem.hospital_management_system.services.BillService;

@RestController
@RequestMapping("api/v1/bills")
public class BillController {
	
	private BillService billService;
	
	public BillController(BillService billService) {
		this.billService = billService;
	}

	@GetMapping
	public List<Bill> getAllBills() {
		return null;
	}
	
	@PostMapping
	public Bill createBill(@RequestBody Bill bill) {
		return null;
	}
	
	@GetMapping("/{id}")
	public Bill getBillById(@PathVariable Long id) {
		return null;
	}
	
	@PutMapping("/{id}")
	public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteBill(@PathVariable Long id) {
		
	}
	
}
