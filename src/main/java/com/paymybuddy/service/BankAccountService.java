package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.CustomUserDetails;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.BankAccountRepository;
import com.paymybuddy.repository.UserRepository;

@Service
public class BankAccountService {
	
	@Autowired
	private BankAccountRepository bankAccountRepo;
	@Autowired
	private UserRepository userRepo;
	public void update(BankAccount bankAccount) {
		
		bankAccount.setUser(getLoggedUser());
		bankAccountRepo.save(bankAccount);
	}
	
	public User getLoggedUser() {
		CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return userRepo.findByEmail(principal.getUsername());
	}
}
