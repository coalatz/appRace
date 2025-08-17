package com.appRacer.Run.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appRacer.Run.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{

}
