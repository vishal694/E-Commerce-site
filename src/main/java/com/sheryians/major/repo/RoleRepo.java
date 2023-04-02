package com.sheryians.major.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.model.RoleModel;

public interface RoleRepo extends JpaRepository<RoleModel, Integer> {

	
}
