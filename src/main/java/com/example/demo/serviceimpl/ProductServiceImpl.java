package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	boolean flag;
	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> fetchAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product fetchProduct(int id) {
		// TODO Auto-generated method stub
		Optional<Product> product=productRepository.findById(id);
		if(product.isPresent()) {
			Product p=product.get();
			return p;
		}
		else {
			throw new ProductNotFoundException("Product with "+ id +" is not present");
		
	}
		
	}

//	@Override
//	public Product fetchProduct(int id) {
//		Product product = null;
//		if(id <0) {
//			flag = productRepository.existsById(id);
//		}
//		if(flag) 
//			product = productRepository.findById(id).get();
//		else
//			throw new ProductNotFoundException("Product Not Found");
//		return product;
//	}

	

	
}
