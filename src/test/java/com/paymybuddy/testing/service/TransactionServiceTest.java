package com.paymybuddy.testing.service;



import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.paymybuddy.model.TransactionFormData;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.TransactionService;



@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private TransactionRepository transactionRepo;
	
	/*@Mock
	private TransactionService transactionService;*/
	
	@Autowired
	@InjectMocks
	private TransactionService transactionService;
	
	
	
	private User user1;
	private User user2;
	List<User> users;

	@BeforeEach
	public void SetUp() {
		
		user1 = new User();
		user1.setEmail("user1@oc.com");
		user1.setPassword("password");
		user1.setFirstName("user");
		user1.setLastName("one");
		user1.setBalance(new BigDecimal(1000));
		
		
		user2 = new User();
		user2.setEmail("user2@oc.com");
		user2.setPassword("password");
		user2.setFirstName("user");
		user2.setLastName("two");
		
		
		
	}
	@AfterEach
    public void tearDown() {
    user1 = user2 = null;
    users = null;
    }
	

	
	@Test
	public void performSuccessTransactionTest() {  
		TransactionFormData tfd = new TransactionFormData(); 
		tfd.setConnection("user two");
		  tfd.setAmount("500"); 
		  String retour = transactionService.performTransaction(tfd,user1);
		  System.out.println("retour = "+ retour);
		  assertEquals(retour,"success");
	}
	
	@Test
	public void performFailTransactionTest() {  
		TransactionFormData tfd = new TransactionFormData(); 
		tfd.setConnection("user two");
		  tfd.setAmount("1500"); 
		  String retour = transactionService.performTransaction(tfd,user1);
		  System.out.println("retour = "+ retour);
		  assertEquals(retour,"fail");
	}
}
