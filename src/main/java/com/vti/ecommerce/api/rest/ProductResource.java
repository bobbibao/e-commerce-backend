package com.vti.ecommerce.api.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.services.IProductService;
import com.vti.ecommerce.services.dto.ProductDto;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

	private final IProductService productService;

	public ProductResource(IProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
		System.out.println("idasd: " + id);
		Optional<ProductDto> product = productService.getById(id);
		System.out.println("product: " + product);
		return product.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProduct(){
		return ResponseEntity.ok(productService.getAll());
	}
}