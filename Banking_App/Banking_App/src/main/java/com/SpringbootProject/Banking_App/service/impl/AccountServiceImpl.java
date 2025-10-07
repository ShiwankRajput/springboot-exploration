package com.SpringbootProject.Banking_App.service.impl;

import org.springframework.stereotype.Service;

import com.SpringbootProject.Banking_App.dto.AccountDTO;
import com.SpringbootProject.Banking_App.entity.Account;
import com.SpringbootProject.Banking_App.mapper.AccountMapper;
import com.SpringbootProject.Banking_App.repository.AccountRepository;
import com.SpringbootProject.Banking_App.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	// convert accountDto <-> JpaAccountEntity <-> database , using Mapper class
	@Override
	public AccountDTO createAccount(AccountDTO accountDto) {
		
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDTO(savedAccount);
		
	}

	@Override
	public AccountDTO getAccountById(Long id) {
		Account account = accountRepository.
				findById(id).
				orElseThrow(() -> new RuntimeException("Account Does not exists"));
		
		return AccountMapper.mapToAccountDTO(account);
				
	}

}
