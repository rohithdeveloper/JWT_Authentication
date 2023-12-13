package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.example.demo.serviceimpl.JwtService;

@RestController
@RequestMapping("/products" )
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to springboot security";
	}
	@PostMapping("/save")
	public Product saveProducts(@RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	//@Secured("ROLE_USER")
	@GetMapping("/fetchAll")
	public List<Product> fetchProducts(){
		List<Product> list=productService.fetchAllProducts();
		return list;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/fetch/{id}")
	public Product fetchProductById(@PathVariable int id){
		return productService.fetchProduct(id);
	}
	
	@PostMapping(value="/auth",consumes = "application/json")
	public String generateToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
		//return jwtService.generateToken(authRequest.getUsername());
			String token = jwtService.generateToken(authRequest.getUsername());
	        System.out.println("Generated Token: " + token);
	        return token;
		}else {
			throw new UsernameNotFoundException("Invalid User");
			//return "Invalid User";
		}
	}
	
}