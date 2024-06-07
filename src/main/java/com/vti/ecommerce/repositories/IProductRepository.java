package com.vti.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.Product;


@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
//	Product findById(Long productID);
//	ProductDto findById(String productID);
//	List<Product> findAll();
//	boolean save(Product product);
//	boolean deleteById(Long productID);
    List<Product> findByProductNameContaining(String search);
	
}
