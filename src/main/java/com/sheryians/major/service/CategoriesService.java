package com.sheryians.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.CategoriesModel;
import com.sheryians.major.repo.CategoriesRepo;

@Service
public class CategoriesService {
	
	@Autowired
	CategoriesRepo categoriesRepo;
	
	public List<CategoriesModel> getCategories(){
		return categoriesRepo.findAll();
	}
	
	public void addCategories(CategoriesModel categories) {
		categoriesRepo.save(categories);
	}

	public void removeCategoriesById(int id) {
		categoriesRepo.deleteById(id);
	}
	
	public Optional<CategoriesModel> getCategories(int id){
		return categoriesRepo.findById(id);
	}
}
