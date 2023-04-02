package com.sheryians.major.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sheryians.major.dto.productDTO;
import com.sheryians.major.model.ProductModel;
import com.sheryians.major.service.CategoriesService;
import com.sheryians.major.service.ProductService;

@Controller
public class ProductController {

	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	
	@Autowired
	ProductService productSerice;
	
	@Autowired
	CategoriesService categoriesService;
	
	@GetMapping("/admin/products")
	public String getProduct(Model model) {
		model.addAttribute("products", productSerice.getProducts());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String getProductAdd(Model model) {
		model.addAttribute("productDTO",new productDTO());
		model.addAttribute("categories", categoriesService.getCategories());
		return "productsAdd";
	}
	
	
	@PostMapping("/admin/products/add")
	public String postproductAdd(@ModelAttribute("productDTO") productDTO productDTOs,@RequestParam("productImage")MultipartFile file,@RequestParam("imgName")String imgName) throws IOException {
	
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileName = Paths.get(uploadDir,imageUUID);
			Files.write(fileName, file.getBytes());
		}else {
			imageUUID = imgName;
		}
		
		productDTOs.setImageName(imageUUID);
		productSerice.productAdd(productDTOs);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String productDelete(@PathVariable Long id) {
		productSerice.productDelete(id);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable Long id, Model model) {
		
		ProductModel product = productSerice.getProductById(id).get();
		
		productDTO productDto = new productDTO();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setDescription(product.getDescription());
		productDto.setPrice(product.getPrice());
		productDto.setWeight(product.getWeight());
		productDto.setImageName(product.getImageName());
		productDto.setDescription(productDto.getDescription());
	    productDto.setCategoryId(product.getCategory().getId());
	    
	    model.addAttribute("categories", categoriesService.getCategories());
	    model.addAttribute("productDTO",productDto);
		return "productsAdd";
	}
}
