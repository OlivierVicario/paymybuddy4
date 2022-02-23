package com.paymybuddy.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.CustomUserDetails;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.TransactionFormData;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.BankAccountRepository;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.repository.UserRepository;

@Service
public class UserService {

	public User loggedUser;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TransactionService transactionService;

	@Autowired
	BankAccountRepository bankAccountRepo;

	public String manageTransaction(User user, TransactionFormData tfd) {
		
		loggedUser = user;
		String[] fullName = tfd.getConnection().split(" ");
		User recipient = userRepo.findByFullName(fullName[0], fullName[1]);
		BigDecimal transactionAmount = new BigDecimal(tfd.getAmount());

		Double dbleCom = Double.valueOf(tfd.getAmount()) * 0.005;
		BigDecimal commission = new BigDecimal(dbleCom);
		String transactionCreationResult = transactionService.performTransaction(tfd, loggedUser);
		if (transactionCreationResult.equals("success")) {
			
			loggedUser.setBalance(loggedUser.getBalance().subtract(transactionAmount).subtract(commission));
			userRepo.save(loggedUser);
			recipient.setBalance(recipient.getBalance().add(transactionAmount));
			userRepo.save(recipient);

			User paymybuddy = userRepo.findByEmail("balance@paymybuddy.com");
			paymybuddy.setBalance(paymybuddy.getBalance().add(commission));
			userRepo.save(paymybuddy);
			
			return "success";
		} else {
			return "fail";
		}

	}

	public User getLoggedUser() {
		CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		return userRepo.findByEmail(principal.getUsername());
	}

	public BankAccount initialize(User user) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setUser(user);
		bankAccount.setBankName("unknow");
		bankAccount.setBic("unknow");
		bankAccount.setIban("unknow");
		bankAccount.setTitle("unknow");
		bankAccount.setBalance(new BigDecimal(1000));
		bankAccountRepo.save(bankAccount);
		user.setBalance(new BigDecimal(500));
		userRepo.save(user);
		return bankAccount;
	}

	public BankAccount findBankAccount(User user) {

		return bankAccountRepo.findByUser(user);
	}

	public void addUserTo(String email) {
		User connection = userRepo.findByEmail(email);
		loggedUser = getLoggedUser();
		loggedUser.getUsersTo().add(connection);
		userRepo.save(loggedUser);
	}

	public void update(User user) {
		userRepo.save(user);
	}

	public void save(User user) {
		userRepo.save(user);
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	public User getUserbyEmail(String email){
		return userRepo.findByEmail(email);		
	}
}
