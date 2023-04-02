package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.pub.GlobleData;
import com.sheryians.major.service.CategoriesService;
import com.sheryians.major.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoriesService categoriesService;
	
	@GetMapping({"/","/home"})
	public String home(Model model) {
		
		model.addAttribute("cartCount",GlobleData.cart.size());
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories",categoriesService.getCategories());
		model.addAttribute("products", productService.getProducts());
		model.addAttribute("cartCount",GlobleData.cart.size());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategories(Model model, @PathVariable int id) {
		model.addAttribute("categories",categoriesService.getCategories());
		model.addAttribute("products", productService.getAllProductsByCategory(id));
		model.addAttribute("cartCount",GlobleData.cart.size());
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable long id) {
		model.addAttribute("product",productService.getProductById(id).get());
		model.addAttribute("cartCount",GlobleData.cart.size());
		return "viewProduct";
	}
	
}
