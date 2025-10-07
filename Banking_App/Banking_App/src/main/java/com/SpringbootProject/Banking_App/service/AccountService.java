package com.SpringbootProject.Banking_App.service;

import com.SpringbootProject.Banking_App.dto.AccountDTO;

public interface AccountService {

	AccountDTO createAccount(AccountDTO account);
	
	AccountDTO getAccountById(Long id);
	
}
