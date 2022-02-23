package com.paymybuddy.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.TransactionFormData;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.repository.UserRepository;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepo1;
	
	@Autowired
	private UserRepository userRepo1;
	
	public List<Transaction> getByUserFrom(User userFrom){
		return transactionRepo1.findByUserFrom(userFrom);
	}
	
	public String performTransaction(TransactionFormData tfd, User userFrom) {
		
		BigDecimal transactionAmount = new BigDecimal(tfd.getAmount());
		Double dbleCom = Double.valueOf(tfd.getAmount()) * 0.005;
		

		if (transactionAmount.doubleValue() + dbleCom <= userFrom.getBalance().doubleValue()) {
			Transaction transaction = new Transaction();
			transaction.setAmount(new BigDecimal(tfd.getAmount()));
			transaction.setDescription(tfd.getDescription());
			transaction.setUserFrom(userFrom);
			String[] fullName = tfd.getConnection().split(" ");
			User userTo = userRepo1.findByFullName(fullName[0], fullName[1]);
			transaction.setUserTo(userTo);
			transactionRepo1.save(transaction);
			return "success";
		} else {
			return "fail";
		}

		
	}
}
