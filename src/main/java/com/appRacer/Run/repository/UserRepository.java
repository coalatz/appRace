package com.appRacer.Run.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.appRacer.Run.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{

	

	
	List<UserModel> findByNameContainingIgnoreCase(String name);


	@Query("SELECT u FROM UserModel u WHERE u.cpf = :cpf ")
	Optional<UserModel> findByCpf(String cpf);
	

}
