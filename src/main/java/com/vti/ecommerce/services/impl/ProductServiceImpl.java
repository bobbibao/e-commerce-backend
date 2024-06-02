package com.vti.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.repositories.IProductRepository;
import com.vti.ecommerce.services.IProductService;
import com.vti.ecommerce.services.dto.ProductDto;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	public ProductServiceImpl(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public boolean insert(ProductDto s) {

		return this.productRepository.save(this.convertToEntity(s)) != null;
	}

	@Override
	public boolean update(ProductDto s) {
		return this.productRepository.save(this.convertToEntity(s)) != null;
	}

	@Override
	public boolean deleteById(Long p) {
		try {
			Product product = this.productRepository.findById(p).get();
			product.setDeleted(false);;
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Optional<ProductDto> getById(Long p) {
		return this.productRepository.findById(p).map(this::convertToDto);
	}

	

	@Override
	public boolean updatePartial(Long p, Map<String, Object> updates) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMany(List<Long> ids) {
		try{
			ids.stream().forEach(id -> {
				this.deleteById(id);
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	private ProductDto convertToDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setProductID(product.getProductID());
		productDto.setProductName(product.getProductName());
		productDto.setDescription(product.getDescription());
		productDto.setInStock(product.isInStock());
		productDto.setRating(product.getRating());
		productDto.setBrandName(product.getBrandName());
		productDto.setImageURL(product.getImageURL());
		// productDto.setPrice(product.getPrices().get(0).getPriceValue());
		try{
			double price = product.getPrices().get(0).getPriceValue();
			productDto.setPrice(price);
		} catch (Exception e) {
			productDto.setPrice(0);
		}
		return productDto;
	}
	private Product convertToEntity(ProductDto productDto) {
		Product product = new Product();
		product.setProductID(productDto.getProductID());
		product.setProductName(productDto.getProductName());
		product.setDescription(productDto.getDescription());
		product.setInStock(productDto.isInStock());
		product.setRating(productDto.getRating());
		product.setBrandName(productDto.getBrandName());
		product.setImageURL(productDto.getImageURL());
		return product;
	}

	@Override
	public List<ProductDto> getAll() {
		return this.productRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	
//	@Override
//	public Product getProduct(Long id) {
//		return productRepository.findById(id);
//	}
//	@Override
//	public ProductDto createProduct(ProductDto productDto) {
//		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
//	}
//
//	@Override
//	public ProductDto updateProduct(Long id, ProductDto productDto) {
//		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
//	}
//
//	@Override
//	public boolean deleteProduct(Long id) {
//		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
//	}
}