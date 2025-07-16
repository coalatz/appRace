package com.appRacer.Run.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appRacer.Run.model.UserModel;
import com.appRacer.Run.repository.UserRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@PostMapping("register")
	public ResponseEntity<UserModel> registerUser(@RequestBody @Valid UserModel user) {
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findUser(@PathVariable("id") UUID userId) {
		Optional<UserModel> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("users")
	public ResponseEntity<List<UserModel>> listAllUsers() {
		List<UserModel> listUsers = userRepository.findAll();	
		return new ResponseEntity<>(listUsers, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") UUID userId) {
		Optional<UserModel> user = userRepository.findById(userId);
		if(user.isPresent()) {
			userRepository.deleteById(userId);
			return new ResponseEntity<>("user deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<?> updateUsario(@RequestBody @Valid UserModel user, @PathVariable("id") UUID userId) {
		Optional<UserModel> foundOpt = userRepository.findById(userId);
		if(foundOpt.isPresent()) {
			UserModel found = foundOpt.get();
			found.setName(user.getName());
            found.setCpf(user.getCpf());
            found.setAge(user.getAge());
            found.setHeight(user.getHeight());
            found.setWeight(user.getWeight());
            found.setImc(user.getImc());

            return new ResponseEntity<>(found, HttpStatus.OK);

		}
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}


}
