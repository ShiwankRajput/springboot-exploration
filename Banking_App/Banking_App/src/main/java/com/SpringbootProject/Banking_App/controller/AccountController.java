package com.SpringbootProject.Banking_App.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// add account REST-API -->
	
	@GetMapping("{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
		
	    return ResponseEntity.ok(accountService.getAccountById(id));
	    
	}

	
	@PostMapping
	public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDto){
		
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
		
	}
	
}
