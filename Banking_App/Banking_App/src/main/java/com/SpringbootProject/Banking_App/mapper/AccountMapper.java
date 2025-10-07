package com.SpringbootProject.Banking_App.mapper;

import com.SpringbootProject.Banking_App.dto.AccountDTO;
import com.SpringbootProject.Banking_App.entity.Account;

public class AccountMapper {

	// convert accountDTO -> entity
	public static Account mapToAccount(AccountDTO accountDto) {
		
		Account account = new Account(
					accountDto.getId(),
					accountDto.getAccountHolderName(),
					accountDto.getBalance()
				);
		
		return account;
		
	}
	
	// convert entity -> accountDTO
	public static AccountDTO mapToAccountDTO(Account account) {
		
		AccountDTO accountDto = new AccountDTO(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		
		return accountDto;
		
	}
	
}
