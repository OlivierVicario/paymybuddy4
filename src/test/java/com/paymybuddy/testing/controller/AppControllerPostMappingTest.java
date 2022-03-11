package com.paymybuddy.testing.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AppControllerPostMappingTest {


	@Autowired
	private MockMvc mvc;

	@WithMockUser(value = "spring")
	@Test
	public void givenAuthenticated_whenCallUpdate_profileWithSecured_thenOk() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post("/private/update_profile").with(csrf())).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		}
	/*
	@WithMockUser(value = "spring")
	@Test
	public void givenAuthenticated_whenCallProcess_paiementWithSecured_thenOk() throws Exception {
		 
		mvc.perform(MockMvcRequestBuilders.post("/private/process_paiement").with(csrf())).andExpect(MockMvcResultMatchers.status().is(302));
		}
		
	@WithMockUser(value = "spring")
	@Test
	public void givenAuthenticated_whenCallProcess_add_connectionWithSecured_thenOk() throws Exception {
		 
		mvc.perform(MockMvcRequestBuilders.post("/private/process_add_connection").with(csrf())).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		}
	*/	
	@WithMockUser(value = "spring")
	@Test
	public void givenAuthenticated_whenCallUpdate_bankaccounWithSecured_thenOk() throws Exception {
		 
		mvc.perform(MockMvcRequestBuilders.post("/private/update_bankaccount").with(csrf())).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		}
		
	@WithMockUser(value = "spring")
	@Test
	public void givenAuthenticated_whenCallupdate_balancesWithSecured_thenOk() throws Exception {
		 
		mvc.perform(MockMvcRequestBuilders.post("/private/update_balances").with(csrf())).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		}
	
	
	
}
