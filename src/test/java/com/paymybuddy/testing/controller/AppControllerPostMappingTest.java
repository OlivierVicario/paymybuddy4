package com.paymybuddy.testing.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.paymybuddy.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppControllerPostMappingTest {
    @Autowired
    private UserService service;
    
    @WithMockUser(username="spring")
    @Test
    public void givenAuthenticated_whenCallServiceWithSecured_thenOk() {
        //assertThat(service.sayHelloSecured()).isNotBlank();
    }
}
