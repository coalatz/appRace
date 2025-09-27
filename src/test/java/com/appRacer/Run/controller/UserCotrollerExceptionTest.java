package com.appRacer.Run.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import static org.mockito.ArgumentMatchers.eq;

import com.appRacer.Run.controller.UserController;
import com.appRacer.Run.model.UserModel;
import com.appRacer.Run.model.UserPatchModel;
import com.appRacer.Run.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.NoResultException;

@WebMvcTest(UserController.class)
public class UserCotrollerExceptionTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean 
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    
	// fails tests
	@Test
	void shouldSaveUserCpfExisting() throws Exception {
		UUID fakeId = UUID.randomUUID();
		UserModel user = new UserModel(fakeId ,"coala", "09230550607", 20, 1.79f, 93.0f, 23.0f);
		String userJson = objectMapper.writeValueAsString(user);
		
			when(userService.save(any(UserModel.class)))
			.thenThrow(new IllegalArgumentException("CPF already registered"));

		mockMvc.perform(post("/user/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson))
		
		.andExpect(status().isConflict())
		.andExpect(content().string(containsString("CPF already registered")));
		verify(userService).save(any(UserModel.class));
		
	}
	
	@Test
	void shouldSaveUserinvalidCpf() throws Exception{
		UUID fakeId = UUID.randomUUID();
		UserModel user = new UserModel(fakeId ,"coala", "0923t550607", 20, 1.79f, 93.0f, 23.0f);
		String userJson = objectMapper.writeValueAsString(user);
		
		when(userService.save(any(UserModel.class)))
		.thenThrow(new IllegalArgumentException("CPF must be composed of digits"));
		
		mockMvc.perform(post("/user/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(userJson))
		
		.andExpect(status().isConflict())
		.andExpect(content().string(containsString("CPF must be composed of digits")));
		verify(userService).save(any(UserModel.class));
	}
	
	@Test
	void shoudSaveUserArgumentNotBlank() throws Exception {
		UserModel user = new UserModel("", "09240550546", 30, 1.79f, 50.0f, 23.0f);
		String userJson = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/user/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson))
		.andExpect(status().isBadRequest())
		.andExpect(content().string(containsString("mandatory name field")));
	}
	
	@Test
	void shouldSaveUserArgumentMin() throws Exception {
		UserModel user = new UserModel("a", "09240550546", 30, 1.79f, 50.0f, 23.0f);
		String userJson = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/user/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson))
		
		.andExpect(status().isBadRequest())
		.andExpect(content().string(containsString("name: size must be between 5 and 64")));
				
	}
	
	@Test
	void shouldSaveUserArgumentMax() throws Exception {
		UserModel user = new UserModel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "09240550546", 30, 1.79f, 50.0f, 23.0f);
		String userJson = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/user/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson))
		
		.andExpect(status().isBadRequest())
		.andExpect(content().string(containsString("name: size must be between 5 and 64")));
				
	}
	
	
	@Test
	void shouldSaveUserArgumentNotNull() throws Exception {
		UserModel user = new UserModel("a", "09240550546", null, 1.79f, 50.0f, 23.0f);
		String userJson = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/user/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson))
		
		.andExpect(status().isBadRequest())
		.andExpect(content().string(containsString("mandatory age field")));
				
	}
	
	@Test
	void shouldUserNotFoundListId() throws Exception {
		UUID fakeId = UUID.randomUUID();
		
		mockMvc.perform(get("/user/{id}", fakeId)
				.contentType(MediaType.APPLICATION_JSON))
		
		.andExpect(status().isNotFound())
		.andExpect(content().string(containsString("User not found")));
		verify(userService).UserFindId(fakeId);
	}
	
	@Test
	void shouldUserCpfNotExist() throws Exception {
		String cpfFake = "092.406.704-05";
		
		when(userService.finUserbyCpf(cpfFake))
			.thenThrow( new NoResultException("User Not Found"));
		
		mockMvc.perform(get("/user/cpf/{cpf}", cpfFake)
				.contentType(MediaType.APPLICATION_JSON))
		
		.andExpect(status().isNotFound())
		.andExpect(content().string(containsString("User Not Found")));
		verify(userService).finUserbyCpf(cpfFake);
	}
	
	@Test
	void shouldFailToDeleteUserNotFound() throws Exception {
	    UUID fakeId = UUID.randomUUID();
	    
	    when(userService.userDelete(any(UUID.class))).thenReturn(0);
	    
	    mockMvc.perform(delete("/user/delete/{id}", fakeId) 
	            .contentType(MediaType.APPLICATION_JSON))
	    
	    .andExpect(status().isNotFound())
	    .andExpect(content().string(containsString("User not found")));
	    verify(userService).userDelete(fakeId);
	}
	
	@Test
	void shouldFailToUpdateUser_ReturnNotFound() throws Exception {
	    UUID userId = UUID.randomUUID();

	    UserPatchModel patchModel = new UserPatchModel();
	    patchModel.setName("Nomee");
	    String patchJson = objectMapper.writeValueAsString(patchModel);

	    when(userService.updateUser(any(UserPatchModel.class), eq(userId))).thenReturn(null);

	    mockMvc.perform(patch("/user/update/{id}", userId)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(patchJson))

	    .andExpect(content().string(containsString("User not found")));
	    verify(userService).updateUser(any(UserPatchModel.class), eq(userId));
	}
	
}
