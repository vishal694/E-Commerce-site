package com.sheryians.major.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.model.CategoriesModel;

public interface CategoriesRepo extends JpaRepository<CategoriesModel, Integer> {

}
