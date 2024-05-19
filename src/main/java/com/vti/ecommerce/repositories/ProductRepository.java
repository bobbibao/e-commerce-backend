package com.vti.ecommerce.repositories;

import java.util.List;

import com.vti.ecommerce.domains.Product;



public interface ProductRepository {
	Product findById(Long productID);
	List<Product> findAll();
	boolean save(Product product);
	boolean deleteById(Long productID);
	
}
