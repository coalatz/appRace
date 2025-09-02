package com.appRacer.Run.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appRacer.Run.model.UserModel;
import com.appRacer.Run.model.UserPatchModel;
import com.appRacer.Run.repository.UserRepository;
import com.appRacer.Run.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.media.MediaType;
import jakarta.validation.Valid;


@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;

	@PostMapping("register")
	@Operation(summary = "Register a new user")
	@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "201",
		        description = "Register a new user",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(implementation = UserModel.class)
		        )),
		    @ApiResponse(
		        responseCode = "400",
		        description = "Invalid data",
		        content = @Content(
		            mediaType = "application/json",
		            examples = @ExampleObject("""
		            {
		              "errors": [
		                "height: height: must be greater than or equal to 1",
		                "weight: weight: must be greater than or equal to 20",
		                "name: name: size must be between 10 and 64",
		                "cpf: CPF: size must be between 11 and 15",
		                "age: age: must be greater than or equal to 4"
		              ]
		            }""")
		        )
		    )
		})
	public ResponseEntity<UserModel> registerUser(@RequestBody @Valid UserModel user) {
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@ApiResponses( value = {
			@ApiResponse(responseCode = "404", 
				description = "User not found",
				content = @Content(
						mediaType = "application/json",
						examples = @ExampleObject("{ \"mensagem\": \"User not found\"}")
					)),
			@ApiResponse(responseCode = "200",
				description = "list user by id",
				content = @Content(
						mediaType = "application/json",
						schema = @Schema(implementation = UserModel.class)))	
	})
	@Operation(summary = "List user by id")
	@GetMapping("{id}")
	public ResponseEntity<?> findUser(@PathVariable("id") UUID userId) {
		UserModel user = userService.UserFindId(userId);
		if(user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}
	
	
	@ApiResponses( value = {
			@ApiResponse(responseCode = "404", 
				description = "User not found",
				content = @Content(
						mediaType = "application/json",
						examples = @ExampleObject("{ \"mensagem\": \"User not found\"}")
					)),
			@ApiResponse(responseCode = "200",
				description = "list user by cpf",
				content = @Content(
						mediaType = "application/json",
						schema = @Schema(implementation = UserModel.class)))	
	})
	@Operation(summary = "List user by cpf")
	@GetMapping("cpf/{cpf}")
	public ResponseEntity<UserModel> findUserByCpf(@PathVariable String cpf) {
		UserModel user = userService.finUserbyCpf(cpf);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@Operation(summary = "List all users")
	@ApiResponse(responseCode = "200",
			description = "list all users",
			content = @Content(
					mediaType = "application/json",
					schema = @Schema(implementation = UserModel.class)))
	@GetMapping("users")
	public ResponseEntity<List<UserModel>> listAllUsers() {
		List<UserModel> listUsers = userService.usersFindAll();	
		return new ResponseEntity<>(listUsers, HttpStatus.OK);
	}
	
	@Operation(summary = "Delete user by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "delete user by id",
					content = @Content(
							mediaType = "application/json",
							examples = @ExampleObject("{ \"mensagem\": \"User deleted successfully\"}"))),
			@ApiResponse(responseCode = "404", 
					description = "user not found",
					content = @Content(
							mediaType = "application/json",
							examples = @ExampleObject("{ \"mensagem\": \"User not found\"}")))
	})
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") UUID userId) {
		int user = userService.userDelete(userId);
		
		if(user == 1) {return new ResponseEntity<>("user deleted successfully", HttpStatus.OK);}
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Update user by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404",
					description = "user not found",
					content = @Content(
							mediaType = "application/json",
							examples = @ExampleObject("{ \"mensagem\": \"User not found\"}"))),
			@ApiResponse(responseCode = "200",
					description = "update user by id",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = UserModel.class))),
			@ApiResponse(
			        responseCode = "400",
			        description = "Invalid data",
			        content = @Content(
			            mediaType = "application/json",
			            examples = @ExampleObject("""
			            {
			              "errors": [
			                "height: height: must be greater than or equal to 1",
			                "weight: weight: must be greater than or equal to 20",
			                "name: name: size must be between 10 and 64",
			                "cpf: CPF: size must be between 11 and 15",
			                "age: age: must be greater than or equal to 4"
			              ]
			            }""")
			        )
			    )
	})
	@PatchMapping("update/{id}")
	public ResponseEntity<?> updateUser(@RequestBody UserPatchModel user, @PathVariable("id") UUID userId) {
		UserModel userUp = userService.updateUser(user, userId);
		
		if(userUp != null) {return new ResponseEntity<>(userUp, HttpStatus.OK);}
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}
}
