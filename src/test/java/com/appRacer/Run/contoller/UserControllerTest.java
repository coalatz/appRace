package com.appRacer.Run.contoller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.appRacer.Run.controller.UserController;
import com.appRacer.Run.model.UserModel;
import com.appRacer.Run.model.UserPatchModel;
import com.appRacer.Run.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	UserController userController;
	
	// successful tests
	@Test
	void shouldSaveUser() {
		UserModel user = new UserModel("coala", "09230550607", 20, 1.79f, 93.0f, 23.0f);
		
		ResponseEntity<UserModel> response = userController.registerUser(user);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService).save(user);
	}
	
	@Test
	void shouldFindUserId() {
		UUID fakeId = UUID.randomUUID();
		UserModel user = new UserModel(fakeId ,"coala", "09230550607", 20, 1.79f, 93.0f, 23.0f);
		
		when(userService.UserFindId(user.getUserId())).thenReturn(user);
		
		ResponseEntity<?> response = userController.findUser(fakeId);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
		verify(userService).UserFindId(fakeId);
	}
	
	@Test
	void shouldFindUserByCpf() {
		UserModel user = new UserModel("coala", "09230550607", 20, 1.79f, 93.0f, 23.0f);
		
		when(userService.finUserbyCpf(user.getCpf())).thenReturn(user);
		
		ResponseEntity<?> response = userController.findUserByCpf(user.getCpf());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
		verify(userService).finUserbyCpf(user.getCpf());
	}
	
	@Test
	void sholdListAllUsers() {
		UserModel user = new UserModel("coala", "09230550607", 20, 1.79f, 93.0f, 23.0f);
		List<UserModel> listUser = new LinkedList<>();
		listUser.add(user);
		
		when(userService.usersFindAll()).thenReturn(listUser);
		
		ResponseEntity<List<UserModel>> response = userController.listAllUsers();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(listUser, response.getBody());
		verify(userService).usersFindAll();
		
	}
	
	@Test
	void sholdListuserName() {
		UserModel user = new UserModel("coala", "09230550607", 20, 1.79f, 93.0f, 23.0f);
		List<UserModel> listUser = new LinkedList<>();
		listUser.add(user);
		
		when(userService.findUserByName(user.getName())).thenReturn(listUser);
		
		ResponseEntity<List<UserModel>> response = userController.listUserName(user.getName());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(listUser, response.getBody());
		verify(userService).findUserByName(user.getName());
	}
	
	@Test
	void shouldUserDeleteId() {
		UUID fakeId = UUID.randomUUID();
		UserModel user = new UserModel(fakeId ,"coala", "09230550607", 20, 1.79f, 93.0f, 23.0f);	
		user.setUserId(fakeId);
		
		
		when(userService.userDelete(fakeId)).thenReturn(1);
		
		ResponseEntity<String> response = userController.deleteUser(fakeId);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("user deleted successfully", response.getBody());
		verify(userService).userDelete(fakeId);
	}
	
	@Test
	void shouldUpdateId() {
	    UUID fakeId = UUID.randomUUID();
	    UserPatchModel userPatch = new UserPatchModel();
	    userPatch.setName("paulo");
	    
	    UserModel updatedUser = new UserModel(fakeId ,"paulo", "09230550607", 20, 1.79f, 93.0f, 23.0f);

	    when(userService.updateUser(userPatch, fakeId)).thenReturn(updatedUser);
	    
	    ResponseEntity<?> response = userController.updateUser(userPatch, fakeId);

	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(updatedUser, response.getBody());
	    verify(userService).updateUser(userPatch, fakeId);
	}
	

	
}
