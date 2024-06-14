package com.vti.ecommerce.api.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.services.IProductService;
import com.vti.ecommerce.services.dto.ProductDto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductResource {

	private final IProductService productService;

	public ProductResource(IProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProduct(){
		return ResponseEntity.ok(productService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
		System.out.println("idasd: " + id);
		Optional<ProductDto> product = productService.getById(id);
		System.out.println("product: " + product);
		return product.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}
	
	// `http://localhost:8080/products?_page=1&_limit=8`
	@GetMapping(params = {"_page", "_limit"})
	public ResponseEntity<List<ProductDto>> getProductByPage(@RequestParam("_page") int page, @RequestParam("_limit") int limit){
		return ResponseEntity.ok(productService.getProductByPage(page, limit));
	}
	
	@GetMapping(params = {"q", "_page", "_limit"})
	public ResponseEntity<List<ProductDto>> searchProduct(@RequestParam("q") String search, @RequestParam("_page") int page, @RequestParam("_limit") int limit){
		return ResponseEntity.ok(productService.searchProduct(search, page, limit));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
		System.out.println("id: " + id);
		System.out.println("updates: " + productDto);
		productDto.setId(id);
		return productService.update(productDto)?
				ResponseEntity.ok(productDto):
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}