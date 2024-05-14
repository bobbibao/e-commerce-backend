package com.vti.ecommerce.services.dto;

import java.util.List;

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
    private List<String> availableSize;
    private ProductCategory category;
    private ProductGender gender;
}
