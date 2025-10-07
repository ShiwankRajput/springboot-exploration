package com.SpringbootProject.Banking_App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringbootProject.Banking_App.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
