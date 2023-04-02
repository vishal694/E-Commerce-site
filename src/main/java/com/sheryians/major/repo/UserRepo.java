package com.sheryians.major.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sheryians.major.model.UserModel;

public interface UserRepo extends JpaRepository<UserModel, Integer> {
	
	@Query(value = "SELECT * FROM USERS WHERE EMAIL = :email",nativeQuery = true)
	Optional<UserModel> findUserByEmail(String email);
}
