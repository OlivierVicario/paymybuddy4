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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginControllerTest {

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
	public void givenAuthRequestOnHome_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/home").contentType(MediaType.APPLICATION_JSON)).andExpect(authenticated())
				.andExpect(status().isOk());
	}

	// @WithMockUser annotation is not intended to execute authentication. It
	// creates a user which is authenticated already.
	// By default his credentials are user : password
	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnRegister_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/register").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	public void givenAuthRequestOnLogin_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/login").contentType(MediaType.APPLICATION_JSON)).andExpect(authenticated())
				.andExpect(status().isOk());
	}

}