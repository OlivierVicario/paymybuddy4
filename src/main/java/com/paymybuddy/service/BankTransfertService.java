package com.paymybuddy.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.BankTransfert;
import com.paymybuddy.model.CustomUserDetails;
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
	
	public void manageBankTransfert(BankTransfert bankTransfert) {
		User loggedUser = getLoggedUser();

		BigDecimal amount = bankTransfert.getAmount();
		loggedUser.setBalance(loggedUser.getBalance().add(amount));
		userRepo.save(loggedUser);
		
		BankAccount bankAccount = bankAccountRepo.findByUser(loggedUser);
		bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
		bankAccountRepo.save(bankAccount);
		
		bankTransfert.setUser(loggedUser);
		bankTransfertRepo.save(bankTransfert);
	}
	
	public User getLoggedUser() {
		CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return userRepo.findByEmail(principal.getUsername());
	}
}
