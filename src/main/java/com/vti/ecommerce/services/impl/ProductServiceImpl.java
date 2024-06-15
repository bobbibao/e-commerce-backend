package com.vti.ecommerce.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.repositories.IProductRepository;
import com.vti.ecommerce.services.IProductService;
import com.vti.ecommerce.services.dto.ProductDto;
import com.vti.ecommerce.services.dto.ReviewDto;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	public ProductServiceImpl(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public boolean insert(ProductDto s) {
		try {
			Product product = new Product();
			product.setProductCode(s.getProductCode());
			product.setProductName(s.getName());
			product.setDescription(s.getDescription());
			product.setInStock(s.isInStock());
			product.setRating(s.getRating());
			product.setBrandName(s.getBrandName());
			product.setImageURL(s.getImageUrl());
			product.setCreateAt(s.getProductionDate());
			product.setAvailableSize(s.getAvailableSizes());
			product.setAdditionalImageURLs(s.getAdditionalImageUrls());
			// product.setCategory(s.getCategory());
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(ProductDto s) {
		try {
			Product product = this.productRepository.findById(s.getId()).get();
			product.setProductCode(s.getProductCode());
			product.setProductName(s.getName());
			product.setDescription(s.getDescription());
			// product.setInStock(s.isInStock());
			product.setRating(s.getRating());
			product.setBrandName(s.getBrandName());
			product.setImageURL(s.getImageUrl());
			product.setCreateAt(s.getProductionDate());
			// product.setAvailableSize(s.getAvailableSizes());
			// product.setAdditionalImageURLs(s.getAdditionalImageUrls());
			// product.setCategory(s.getCategory());
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
		try {
			Product product = this.productRepository.findById(p).get();
			updates.forEach((key, value) -> {
				switch (key) {
				case "name" -> product.setProductName((String) value);
				case "description" -> product.setDescription((String) value);
				case "inStock" -> product.setInStock((boolean) value);
				case "rating" -> product.setRating((int) value);
				case "brandName" -> product.setBrandName((String) value);
				case "imageUrl" -> product.setImageURL((String) value);
				case "productionDate" -> product.setCreateAt((LocalDate) value);
				case "availableSizes" -> {
                                }
				case "additionalImageUrls" -> {
                                }
				case "category" -> {
                                }
					
				default -> {
                                }
				}
                            // product.setAvailableSize((int) value);
                            // product.setAdditionalImageURLs((String) value);
                            // product.setCategory((String) value);
                            			});
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			return false;
		}
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
		productDto.setId(product.getProductID());
		productDto.setProductCode(product.getProductCode());
		productDto.setName(product.getProductName());
		productDto.setDescription(product.getDescription());
		productDto.setInStock(product.isInStock());
		productDto.setRating(product.getRating());
		productDto.setBrandName(product.getBrandName());
		productDto.setImageUrl(product.getImageURL());
		productDto.setProductionDate(product.getCreateAt());
		productDto.setAvailableSizes(product.getAvailableSize());
		productDto.setTotalReviewCount(product.getCustomerReviews().size());
		productDto.setAdditionalImageUrls(product.getAdditionalImageURLs());
		productDto.setReviews(product.getCustomerReviews().stream().map(review -> {
			ReviewDto reviewDto = new ReviewDto();
			reviewDto.setUsername(review.getUser().getFirstName());
			reviewDto.setUserImage(review.getUser().getUserImage());
			reviewDto.setLocation(review.getUser().getAddress().getCity());
			reviewDto.setRating(review.getRating());
			reviewDto.setDate(review.getReviewDate().toString());
			reviewDto.setReviewTitle(review.getReviewTitle());
			reviewDto.setReviewText(review.getReviewText());
			return reviewDto;
		}).collect(Collectors.toList()));
		productDto.setCategory(product.getCategory().name());
		productDto.setGender(product.getGender().name());
		
		try{
			double price = product.getPrices().get(0).getPriceValue();
			productDto.setPrice(price);
		} catch (Exception e) {
			productDto.setPrice(0);
		}
		return productDto;
	}

	@Override
	public List<ProductDto> getAll() {
		return this.productRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getProductByPage(int page, int limit) {
		return this.productRepository.findAll().stream().skip((page - 1) * limit).limit(limit).map(this::convertToDto)
				.collect(Collectors.toList());
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

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<ProductDto> searchProduct(String search, int page, int limit) {
		return this.productRepository.findByProductNameContaining(search).stream().skip((page - 1) * limit).limit(limit).map(this::convertToDto).collect(Collectors.toList());
    }
}