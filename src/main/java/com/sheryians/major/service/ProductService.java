package com.sheryians.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sheryians.major.dto.productDTO;
import com.sheryians.major.model.ProductModel;
import com.sheryians.major.model.ProductWithAdmin;
import com.sheryians.major.repo.ProductRepo;
import com.sheryians.major.repo.ProductWithAdminRepo;

@Service
public class ProductService {

	@Autowired
	ProductRepo product;
	
	@Autowired
	CategoriesService categories;
	
	@Autowired
	ProductWithAdminRepo productWithAdminRepo;
	
	public List<ProductModel> getProducts(){
		return product.findAll();
	}
	
	public void addProduct(ProductModel productModel) {
		product.save(productModel);
	}
	
	public void removeProduct(Long id) {
		product.deleteById(id);
	}
	
	public Optional<ProductModel> getProductById(Long id){
		return product.findById(id);
	}
	
	public List<ProductModel> getAllProductsByCategory(int id){
		return product.findAllByCATEGORY_ID(id);
	}
	
	public void productAdd(productDTO productDto) {
		ProductModel productModel = new ProductModel();
		productModel.setCategory(categories.getCategories(productDto.getCategoryId()).get());
		//productModel.setId(productDto.getId());
		productModel.setDescription(productDto.getDescription());
		productModel.setWeight(productDto.getWeight());
		productModel.setPrice(productDto.getPrice());
		productModel.setName(productDto.getName());
		productModel.setImageName(productDto.getImageName());
		
		ProductWithAdmin productWithAdmin = new ProductWithAdmin();
		
		productWithAdmin.setDescription(productModel.getDescription());
		productWithAdmin.setWeight(productModel.getWeight());
		//productWithAdmin.setId(productModel.getId());
		productWithAdmin.setName(productModel.getName());
		productWithAdmin.setImageName(productModel.getImageName());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		productWithAdmin.setEmail(auth.getName());
		productWithAdminRepo.save(productWithAdmin);
		product.save(productModel);
	}
	
	public void productDelete(Long id) {
		product.deleteById(id);
	}
}
