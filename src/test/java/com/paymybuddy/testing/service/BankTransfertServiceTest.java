package com.paymybuddy.testing.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.BankTransfert;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.BankAccountRepository;
import com.paymybuddy.repository.BankTransfertRepository;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.BankTransfertService;
import com.paymybuddy.service.TransactionService;

@ExtendWith(MockitoExtension.class)
public class BankTransfertServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private TransactionRepository transactionRepo;

	@Mock
	private BankAccountRepository bankAccountRepository;

	@Mock
	private BankTransfertRepository bankTransfertRepository;

	@Mock
	private TransactionService transactionService;

	@Autowired
	@InjectMocks
	private BankTransfertService bankTransfertService;

	private User user1;
	private BankTransfert bankTransfert;
	private BankAccount bankAccount;

	@BeforeEach
	public void SetUp() {

		user1 = new User();
		user1.setEmail("user1@oc.com");
		user1.setPassword("password");
		user1.setFirstName("user");
		user1.setLastName("one");
		user1.setBalance(new BigDecimal(1000));

		bankTransfert = new BankTransfert();
		bankTransfert.setAmount(new BigDecimal(500));
		bankTransfert.setUser(user1);

		bankAccount = new BankAccount();
		bankAccount.setBalance(new BigDecimal(1000));
		bankAccount.setUser(user1);
	}

	@AfterEach
	public void tearDown() {
		user1 = null;
		bankTransfert = null;
		bankAccount = null;
	}

	@Test
	public void bankTransfertServiceTest() {

		String retour = bankTransfertService.manageBankTransfert(bankTransfert, user1, bankAccount);

		assertEquals(retour, "success");
	}

}
