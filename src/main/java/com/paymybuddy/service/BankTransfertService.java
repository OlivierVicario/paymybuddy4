package com.paymybuddy.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.BankTransfert;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.BankAccountRepository;
import com.paymybuddy.repository.BankTransfertRepository;
import com.paymybuddy.repository.UserRepository;

@Service
public class BankTransfertService {
	@Autowired
	private BankTransfertRepository bankTransfertRepo;
	
	@Autowired
	private BankAccountRepository bankAccountRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public String manageBankTransfert(BankTransfert bankTransfert,User user, BankAccount bankAccount) {
		User loggedUser = user;

		BigDecimal amount = bankTransfert.getAmount();
		loggedUser.setBalance(loggedUser.getBalance().add(amount));
		userRepo.save(loggedUser);
		
		//BankAccount bankAccount = bankAccountRepo.findByUser(loggedUser);
		bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
		bankAccountRepo.save(bankAccount);
		
		bankTransfert.setUser(loggedUser);
		bankTransfertRepo.save(bankTransfert);
		return "success";
	}
	

}
