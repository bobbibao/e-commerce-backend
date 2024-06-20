package com.vti.ecommerce.services.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.domains.enumeration.ProductGender;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProductDto {
	private long id;
    private String productCode;
    private String name;
    private String description;
    private boolean isInStock;
    private int rating;
    private String gender;
    private String category;
    private String brandName;
    private String imageUrl;
    private LocalDate productionDate;
    private Set<String> availableSizes;
    private int totalReviewCount;
    private int stock;
    private int sold;
    private double price;
    private List<String> additionalImageUrls;
    private List<ReviewDto> reviews;

    public ProductDto() {
    }

    public ProductDto(long id, String productCode, String name, String description, boolean isInStock, int rating,
            String gender, String category, String brandName, String imageUrl, LocalDate productionDate,
            Set<String> availableSizes, int totalReviewCount, double price, List<String> additionalImageUrls,
            List<ReviewDto> reviews, int stock, int sold) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.isInStock = isInStock;
        this.rating = rating;
        this.gender = gender;
        this.category = category;
        this.brandName = brandName;
        this.imageUrl = imageUrl;
        this.productionDate = productionDate;
        this.availableSizes = availableSizes;
        this.totalReviewCount = totalReviewCount;
        this.price = price;
        this.additionalImageUrls = additionalImageUrls;
        this.reviews = reviews;
        this.stock = stock;
        this.sold = sold;
    }

    public Product toEntity() {
        Product product = new Product();
        product.setProductID(id);
        product.setProductCode(productCode);
        product.setProductName(name);
        product.setDescription(description);
        product.setInStock(isInStock);
        product.setRating(rating);
        
        product.setGender(ProductGender.valueOf(name));
        // product.setCategory(category);
        product.setBrandName(brandName);
        product.setImageURL(imageUrl);
        product.setCreateAt(productionDate);
        // product.setAvailableSizes(availableSizes);
        // product.setTotalReviewCount(totalReviewCount);
        // product.setPrice(price);
        // product.setAdditionalImageUrls(additionalImageUrls);
        // product.setReviews(reviews);
        return product;
    }
    
}
