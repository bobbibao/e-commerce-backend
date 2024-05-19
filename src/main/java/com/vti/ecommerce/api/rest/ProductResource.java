package com.vti.ecommerce.api.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.domains.Product;
import com.vti.ecommerce.services.ProductService;
import com.vti.ecommerce.services.dto.ProductDto;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

	private final ProductService productService;

	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<ProductDto> getProduct(Long id) {
		Product product = productService.getProduct(id);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		ProductDto productDto = new ProductDto(product);
		return ResponseEntity.ok(productDto);
	}
	
}
