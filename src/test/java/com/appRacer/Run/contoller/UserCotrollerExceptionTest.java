package com.appRacer.Run.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.appRacer.Run.controller.UserController;
import com.appRacer.Run.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserCotrollerExceptionTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean 
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();
}
