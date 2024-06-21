package com.vti.ecommerce.api.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vti.ecommerce.services.IProductService;
import com.vti.ecommerce.services.dto.ProductDto;
import com.vti.ecommerce.services.dto.ProductForSave;
import com.vti.ecommerce.services.dto.ProductImport;

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
		Optional<ProductDto> product = productService.getById(id);
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

	@PostMapping("/uploadFiles")
	public ResponseEntity<Map<String, Object>> uploadFiles(
		@RequestParam("mainImage") MultipartFile mainImage,
		@RequestParam("additionalImages") List<MultipartFile> additionalImages) throws IOException {

		// Logging to debug received files
		System.out.println("Received main image: " + mainImage.getOriginalFilename());
		System.out.println("Received additional images: " + additionalImages.size());

		// Upload the main image and get the URL
		// split https://
		String mainImageUrl = productService.uploadFile(mainImage).split("https://")[1];

		// Upload additional images and get the URLs
		List<String> additionalImageUrls = new ArrayList<>();
		for (MultipartFile image : additionalImages) {
			String imageUrl = productService.uploadFile(image).split("https://")[1];
			additionalImageUrls.add(imageUrl);
		}

		// Create response map
		Map<String, Object> response = new HashMap<>();
		response.put("mainImage", mainImageUrl);
		response.put("additionalImages", additionalImageUrls);

		return ResponseEntity.ok(response);

	}
	// @PostMapping("/upload")
    // public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
    //     String fileName = productService.uploadFile(file);
	// 	return ResponseEntity.ok("File uploaded: " + fileName);
    // }
	@PostMapping
	public ResponseEntity<ProductForSave> createProduct(@RequestBody ProductForSave productForSave){
		System.out.println("productForSave: " + productForSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productForSave));
	}
	
// 	[Log] Products: – [{product: "203509790", quantity: "11"}, {product: "204349751", quantity: "22"}] (2) (ImportProductStockForm.jsx, line 612)
// [Log] Supplier: – "2" (ImportProductStockForm.jsx, line 613)
	@PostMapping("/import")
	public ResponseEntity<String> importProducts(@RequestBody List<ProductImport> products, @RequestParam("supplier") String supplierId){
		products.forEach(System.out::println);
		productService.importProducts(products, Long.valueOf(supplierId));
		return ResponseEntity.ok("Imported successfully");
	}

}