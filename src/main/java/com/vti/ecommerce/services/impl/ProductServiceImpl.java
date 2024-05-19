package com.vti.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.Product;
import com.vti.ecommerce.repositories.ProductRepository;
import com.vti.ecommerce.services.ProductService;
import com.vti.ecommerce.services.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product getProduct(Long id) {
		return productRepository.findById(id);
	}
	@Override
	public ProductDto createProduct(ProductDto productDto) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
	}

	@Override
	public ProductDto updateProduct(Long id, ProductDto productDto) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
	}

	@Override
	public boolean deleteProduct(Long id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
	}
}