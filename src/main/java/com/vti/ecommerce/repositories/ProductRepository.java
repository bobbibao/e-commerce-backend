package com.vti.ecommerce.repositories;

import java.util.List;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.services.dto.ProductDto;



public interface ProductRepository {
	Product findById(Long productID);
	ProductDto findById(String productID);
	List<Product> findAll();
	boolean save(Product product);
	boolean deleteById(Long productID);
	
}
