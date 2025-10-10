package com.SpringbootProject.Banking_App.service.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public AccountDTO deposit(Long id, Double amount) {
		
		Account account = accountRepository.
				findById(id).
				orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		Double total = account.getBalance() + amount;
		account.setBalance(total);
		
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDTO(savedAccount);
		
	}

	@Override
	public AccountDTO withdraw(Long id, Double amount) {
		
		Account account = accountRepository.
				findById(id).
				orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		if(amount > account.getBalance()) {
			throw new RuntimeException("Insufficient withdraw");
		}
		
		Double total = account.getBalance() - amount;
		account.setBalance(total);
		
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDTO(savedAccount);
	}

	@Override
	public List<AccountDTO> getAllAccounts() {
		
		List<Account> accountList = accountRepository.findAll();
		
		List<AccountDTO> accountDtoList = new ArrayList<>();
		
		for(int i=0; i<accountList.size(); i++) {
			Account account = accountList.get(i);
			AccountDTO accountDto = AccountMapper.mapToAccountDTO(account);
			accountDtoList.add(accountDto);
		}
		
		return accountDtoList;
	}

	@Override
	public void deleteAccountById(Long id) {
		
		Account account = accountRepository.
				findById(id).
				orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		accountRepository.deleteById(id);
		
	}

}
