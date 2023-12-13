package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface ProductService {
	 Product saveProduct(Product product);
	 List<Product> fetchAllProducts();
	 Product fetchProduct(int id);
	
}
