package com.paymybuddy.testing.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AppControllerPostMappingTest {


	@Autowired
	private MockMvc mvc;


	@Test
	public void givenAuthenticated_whenCallServiceWithSecured_thenOk() throws Exception {
		 
		mvc.perform(MockMvcRequestBuilders.post("/update_profile")).andExpect(MockMvcResultMatchers.status().isForbidden());
		}
}
