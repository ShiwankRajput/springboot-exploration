package com.SpringbootProject.Banking_App.service;

import java.util.List;

import com.SpringbootProject.Banking_App.dto.AccountDTO;

public interface AccountService {

	AccountDTO createAccount(AccountDTO accountDto);
	
	AccountDTO getAccountById(Long id);
	
	AccountDTO deposit(Long id, Double amount);
	
	AccountDTO withdraw(Long id, Double amount);
	
	List<AccountDTO> getAllAccounts();
	
	void deleteAccountById(Long id);
	
}
