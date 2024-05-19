package com.vti.ecommerce.services.dto;

import java.util.List;
import java.util.Set;

import com.vti.ecommerce.domains.Product;
import com.vti.ecommerce.domains.enumeration.ProductCategory;
import com.vti.ecommerce.domains.enumeration.ProductGender;

public class ProductDto {
	private Long productId;
    private String productName;
    private String productCode;
    private String description;
    private String brandName;
    private String imageUrl;
    private boolean isInStock;
    private int rating;
    private int totalReviewCount;
    private List<String> additionalImageUrls;
    private Set<String> availableSize;
    private ProductCategory category;
    private ProductGender gender;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.productId = product.getProductID();
        this.productName = product.getProductName();
        this.productCode = product.getProductCode();
        this.description = product.getDescription();
        this.brandName = product.getBrandName();
        this.imageUrl = product.getImageURL();
        this.isInStock = product.isInStock();
        this.rating = product.getRating();
        this.totalReviewCount = product.getTotalReviewCount();
        this.additionalImageUrls = product.getAdditionalImageURLs();
        this.availableSize = product.getAvailableSize();
        this.category = product.getCategory();
        this.gender = product.getGender();
    }
}
