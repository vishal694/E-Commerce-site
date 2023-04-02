package com.sheryians.major.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sheryians.major.model.ProductModel;

public interface ProductRepo extends JpaRepository<ProductModel, Long> {

	@Query(value = "SELECT * FROM PRODUCT_MODEL WHERE category_id = :id",nativeQuery = true)
	List<ProductModel> findAllByCATEGORY_ID(int id);
}
