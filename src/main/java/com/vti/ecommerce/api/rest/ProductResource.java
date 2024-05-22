package com.vti.ecommerce.api.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.services.ProductService;
import com.vti.ecommerce.services.dto.ProductDto;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

	private final ProductService productService;

	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id){
		System.out.println("idasd: " + id);
		Product product = productService.getProduct(id);
		System.out.println("product: " + product);
		return ResponseEntity.ok(product);
	}
	
}