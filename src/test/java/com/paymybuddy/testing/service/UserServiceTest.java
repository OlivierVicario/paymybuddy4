package com.paymybuddy.testing.service;



import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.TransactionFormData;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.BankAccountRepository;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.UserService;



@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private BankAccountRepository bankAccountRepository;
	
	@Mock
	private TransactionRepository transactionRepository;
	
	@Autowired
	@InjectMocks
	private UserService userService;
	
	
	
	private User user1;
	private User user2;
	List<User> users;

	@BeforeEach
	public void SetUp() {
		users = new ArrayList<>();
		user1 = new User();
		user1.setEmail("user1@oc.com");
		user1.setPassword("password");
		user1.setFirstName("user");
		user1.setLastName("one");
		users.add(user1);
		
		user2 = new User();
		user2.setEmail("user2@oc.com");
		user2.setPassword("password");
		user2.setFirstName("user");
		user2.setLastName("two");
		users.add(user2);
		
		
	}
	@AfterEach
    public void tearDown() {
    user1 = user2 = null;
    users = null;
    }
	
	@Test
	void givenUserToAddShouldReturnAddedUser(){
	     //stubbing
	     when(userRepository.save(any())).thenReturn(user1);
	     userService.save(user1);
	     verify(userRepository,times(1)).save(any());
	}
	
	@Test
	public void GivenGetAllUsersShouldReturnListOfAllUsers(){
	     userRepository.save(user1);
	    //stubbing mock to return specific data
	    when(userRepository.findAll()).thenReturn(users);
	    List<User> users1 =userService.getAllUsers();
	    assertEquals(users1,users);
	    verify(userRepository,times(1)).save(user1);
	    verify(userRepository,times(1)).findAll();
	}
	
	@Test
	public void initializeBankAccountTest() {
		
		BankAccount bankAccount = userService.initialize(user1);
		assertEquals(bankAccount.getUser(),user1);
		
	}
	
	@Test
	public void managedTransactionTest() {
		/*
		 * when(userService.getLoggedUser()).thenReturn(user1); TransactionFormData tfd
		 * = new TransactionFormData(); tfd.setConnection("user two");
		 * tfd.setAmount("500"); String retour = userService.manageTransaction(tfd);
		 * System.out.println("retour = "+ retour);
		 */
	}
}
