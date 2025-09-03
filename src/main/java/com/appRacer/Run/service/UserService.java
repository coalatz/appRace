package com.appRacer.Run.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.appRacer.Run.model.UserModel;
import com.appRacer.Run.model.UserPatchModel;
import com.appRacer.Run.repository.UserRepository;
import com.appRacer.Run.utils.CpfUtils;

import jakarta.persistence.NoResultException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CpfUtils cpfUtils;
	
	public UserModel save(UserModel user) {
		Float imc =  user.getWeight() / (user.getHeight() * user.getHeight());
		imc = Math.round(imc * 100f) /100f;
		user.setImc(imc);
		
		if(cpfUtils.compareCpf(user.getCpf()) == true) {
			throw new IllegalArgumentException("CPF already registered");
		}
		
		if(!cpfUtils.isCpf(user.getCpf())) { throw new  IllegalArgumentException("CPF must be composed of digits");}
		
		user.setCpf(cpfUtils.formatCpf(user.getCpf()));
		
	
		return userRepository.save(user);
	}
	
	public UserModel UserFindId(UUID userId) {
		Optional<UserModel> userOpt = userRepository.findById(userId);
		
		if(userOpt.isPresent()) {
			UserModel user = userOpt.get();
			return  user;
		}
		UserModel userNull = null;
		return userNull;
	}
	
	public List<UserModel> usersFindAll() {
		return userRepository.findAll();
	}
	
	public int userDelete(UUID userId) {
		Optional<UserModel> user = userRepository.findById(userId);
		if(user.isPresent()) {
			userRepository.deleteById(userId);
			return 1;
		}
		return 2;
	}
	
	public UserModel updateUser(UserPatchModel user, UUID userId) {
		Optional<UserModel> foundOpt = userRepository.findById(userId);
		if(foundOpt.isPresent()) {
			UserModel found = foundOpt.get();			
			if(found.getName() != user.getName() && user.getName() != null) found.setName(user.getName());
            if(found.getAge() != user.getAge() && user.getAge() != null) found.setAge(user.getAge());
            if(found.getHeight() != user.getHeight() && user.getHeight() != null) found.setHeight(user.getHeight());
            if(found.getWeight() != user.getWeight() && user.getWeight() != null) found.setWeight(user.getWeight());
            userRepository.save(found);
            return found;
		}
		UserModel userNull = null;
		return userNull;
	
	}
	
	public UserModel finUserbyCpf(String cpf) {
		Optional<UserModel> userOpt = userRepository.findUserByCpf(cpf);
		if(userOpt.isPresent()) {
			UserModel user = userOpt.get();
			return user;
		}
		
		throw new NoResultException("User Not Found");
	}

}