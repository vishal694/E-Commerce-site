package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.model.ProductModel;
import com.sheryians.major.pub.GlobleData;
import com.sheryians.major.service.ProductService;

@Controller
public class CartController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
	  GlobleData.cart.add(productService.getProductById((long) id).get());
	  return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	public String cartGet(Model model) {
		model.addAttribute("cartCount",GlobleData.cart.size());
		model.addAttribute("total",GlobleData.cart.stream().mapToDouble(ProductModel :: getPrice).sum());
		model.addAttribute("cart",GlobleData.cart);
		return "cart";
	}
	
	@GetMapping("/cart/removeItem/{id}")
	public String cartItemRemove(@PathVariable int id) {
		GlobleData.cart.remove(id);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("total", GlobleData.cart.stream().mapToDouble(ProductModel :: getPrice).sum());
		return "checkout";
	}
}
