package com.SpringbootProject.Banking_App.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringbootProject.Banking_App.dto.AccountDTO;
import com.SpringbootProject.Banking_App.service.AccountService;

@RestController
@RequestMapping("/api/accounts")    // this is the base URL for all the request
public class AccountController {

	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	
	// get allAccounts RestAPI -->
	
	@GetMapping
	public ResponseEntity<List<AccountDTO>> getAllAccounts(){
		
		return ResponseEntity.ok(accountService.getAllAccounts());
		
	}
	
	
	// get account by Id REST-API -->
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
		
	    return ResponseEntity.ok(accountService.getAccountById(id));
	    
	}

	
	// add account RestAPI
	
	@PostMapping
	public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDto){
		
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
		
	}
	
	
	// delete account RestAPI
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		
		accountService.deleteAccountById(id);
		
		return ResponseEntity.ok("Account successfully deleted");
		
	}
	
	
	// deposit RestAPI
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDTO> deposit(@PathVariable Long id,
			@RequestBody Map<String,Double> requestDeposit){
		
		AccountDTO savedDeposit = accountService.deposit(id, requestDeposit.get("amount"));
		
		return ResponseEntity.ok(savedDeposit);
		
	}
	
	
	// withdraw RestAPI
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id,
			@RequestBody Map<String,Double> requestWithdraw){
		
		AccountDTO withdrawAmount = accountService.withdraw(id, requestWithdraw.get("amount"));
		
		return ResponseEntity.ok(withdrawAmount);
		
	}
	
}
