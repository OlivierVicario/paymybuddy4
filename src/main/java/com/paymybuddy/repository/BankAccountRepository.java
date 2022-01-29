package com.paymybuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.User;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{
	public BankAccount findByUser(User user);
}
