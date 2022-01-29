package com.paymybuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	public List<Transaction> findByUserFrom(User userFrom);
}
