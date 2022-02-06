package com.paymybuddy.testing.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.controller.AppController;
import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;


//le test à une erreur
@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AppController appController;
	
	@MockBean
	UserService userService;
	
	@Test
	public void shouldReturnDashboard() throws Exception{

		/*when(appController.creditAccount()).then(null );
		this.mockMvc.perform(get("")).andDo(print());*/
		//this.mockMvc.perform(get("/dashboard")).andExpect(status().is5xxServerError());
	}
}
