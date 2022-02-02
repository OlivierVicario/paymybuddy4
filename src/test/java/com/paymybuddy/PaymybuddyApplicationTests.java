package com.paymybuddy;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.controller.AppController;
import com.paymybuddy.controller.LoginController;

@SpringBootTest
class PaymybuddyApplicationTests {

	@Autowired
	private LoginController loginController;
	
	@Autowired
	private AppController appController;
	
	@Test
	void contextLoads() {
		assertThat(loginController).isNotNull();
		assertThat(appController).isNotNull();
	}

}
