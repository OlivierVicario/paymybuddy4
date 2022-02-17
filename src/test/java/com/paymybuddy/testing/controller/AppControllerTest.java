package com.paymybuddy.testing.controller;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AppControllerTest {
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnDashBoard_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/dashboard")).andExpect(status().isOk()).andExpect(authenticated())
				.andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnCreditAccount_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/credit_account")).andExpect(status().isOk()).andExpect(authenticated())
				.andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnProfile_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/profile")).andExpect(status().isOk()).andExpect(authenticated())
				.andExpect(status().is2xxSuccessful());

	}

	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnConnections_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/connections")).andExpect(status().isOk()).andExpect(authenticated())
				.andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnPayMyBuddy_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/process_paiement")).andExpect(authenticated());

	}
	
	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnAddConnection_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/process_add_connection")).andExpect(authenticated());

	}
	
	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnUpdateProfile_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/update_profile")).andExpect(authenticated());

	}
	
	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnUpdateBankAccount_shouldSucceedWith200() throws Exception {
		mvc.perform(get("//update_bankaccount")).andExpect(authenticated());

	}
	
	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnUpdateBalance_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/update_balances")).andExpect(authenticated());

	}
}
