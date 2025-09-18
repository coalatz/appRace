package com.appRacer.Run.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appRacer.Run.model.UserModel;
import com.appRacer.Run.repository.UserRepository;

@Component
public class CpfUtils {

	@Autowired
	UserRepository userRepository;
	
	public boolean compareCpf(String cpf) {
		Optional<UserModel> user = userRepository.findByCpf(cpf);
		if(user.isPresent()) {
			return true;
		}
		return false;
	}
	
	public String formatCpf(String cpf) {
		cpf = cpf.replaceAll("\\D", "");
		
		return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
	}
	
	public boolean isCpf(String cpf) {
		if (cpf == null) return false; 
	    return cpf.matches("\\d+"); 
	}
}
