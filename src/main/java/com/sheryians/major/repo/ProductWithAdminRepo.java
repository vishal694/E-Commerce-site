package com.sheryians.major.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.model.ProductWithAdmin;

public interface ProductWithAdminRepo extends JpaRepository<ProductWithAdmin, Long> {

}
