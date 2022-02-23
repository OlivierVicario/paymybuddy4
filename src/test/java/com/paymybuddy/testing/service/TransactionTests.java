package com.paymybuddy.testing.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.TransactionRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class TransactionTests {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TransactionRepository transactionRepo;
	
	@Test
	public void testCreateTransaction() {
		
		Transaction transaction = new Transaction();
		transaction.setDescription("a transaction");
		transaction.setAmount(new BigDecimal(1000));
		transaction.setUserFrom(new User());
		transaction.setUserTo(new User());
		Transaction savedTransaction = transactionRepo.save(transaction);

		Transaction existTransaction = entityManager.find(Transaction.class, savedTransaction.getTransactionId());

		assertThat(savedTransaction.getDescription()).isEqualTo(existTransaction.getDescription());
	}
	
	@Test
	public void testUpdateTransaction() {
		
		Transaction transaction = new Transaction();
		transaction.setDescription("a transaction");
		transaction.setAmount(new BigDecimal(1000));
		transaction.setUserFrom(new User());
		transaction.setUserTo(new User());
		Transaction savedTransaction = transactionRepo.save(transaction);

		savedTransaction.setDescription("another description");
		
		Transaction savedTransaction1 = transactionRepo.save(savedTransaction);
		Transaction existTransaction1 = entityManager.find(Transaction.class, savedTransaction1.getTransactionId());

		assertThat(existTransaction1.getDescription()).isEqualTo("another description");
	}
	
	@Test
	public void testDeleteTransaction() {
		
		Transaction transaction = new Transaction();
		transaction.setDescription("a transaction");
		transaction.setAmount(new BigDecimal(1000));
		transaction.setUserFrom(new User());
		transaction.setUserTo(new User());
		Transaction savedTransaction = transactionRepo.save(transaction);

		transactionRepo.delete(savedTransaction);
		Transaction existTransaction = entityManager.find(Transaction.class, savedTransaction.getTransactionId());
		assertThat(existTransaction).isNull();
	}
}
