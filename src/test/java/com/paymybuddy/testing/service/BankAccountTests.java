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

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.BankAccountRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class BankAccountTests {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BankAccountRepository bankAccountRepo;

	@Test
	public void testCreateBankAccount() {
		
		
		BankAccount bankAccount = new BankAccount();
		bankAccount.setUser(new User());
		bankAccount.setBankName("unknow");
		bankAccount.setBic("unknow");
		bankAccount.setIban("unknow");
		bankAccount.setTitle("unknow");
		bankAccount.setBalance(new BigDecimal(1000));
		BankAccount savedBankAccount = bankAccountRepo.save(bankAccount);

		BankAccount existBankAccount = entityManager.find(BankAccount.class, savedBankAccount.getBankAccountId());

		assertThat(savedBankAccount.getBankName()).isEqualTo(existBankAccount.getBankName());
	}

	@Test
	public void testUpdateBankAccount() {
		BankAccount bankAccount = new BankAccount();
		//bankAccount.setUser(user);
		bankAccount.setBankName("unknow");
		bankAccount.setBic("unknow");
		bankAccount.setIban("unknow");
		bankAccount.setTitle("unknow");
		bankAccount.setBalance(new BigDecimal(1000));
		BankAccount savedBankAccount = bankAccountRepo.save(bankAccount);

		savedBankAccount.setTitle("know1");

		BankAccount savedBankAccount1 = bankAccountRepo.save(savedBankAccount);

		BankAccount existBankAccount1 = entityManager.find(BankAccount.class, savedBankAccount1.getBankAccountId());

		assertThat(existBankAccount1.getTitle()).isEqualTo("know1");
	}

	@Test
	public void testDeleteBankAccount() {
		BankAccount bankAccount = new BankAccount();
		//bankAccount.setUser(user);
		bankAccount.setBankName("unknow");
		bankAccount.setBic("unknow");
		bankAccount.setIban("unknow");
		bankAccount.setTitle("unknow");
		bankAccount.setBalance(new BigDecimal(1000));
		BankAccount savedBankAccount = bankAccountRepo.save(bankAccount);
		
		bankAccountRepo.delete(savedBankAccount);
		BankAccount existBankAccount = entityManager.find(BankAccount.class, savedBankAccount.getBankAccountId());

		assertThat(existBankAccount).isNull();
	}
}

