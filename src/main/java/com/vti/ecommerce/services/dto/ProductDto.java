package com.vti.ecommerce.services.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.domains.enumeration.ProductCategory;
import com.vti.ecommerce.domains.enumeration.ProductGender;

public class ProductDto {
	private long productID;
    private String productName;
    private String description;
    private boolean isInStock;
    private int rating;
    private LocalDate productDate;
    private String brandName;
    private String imageURL;

    // Constructors, Getters, and Setters

    public ProductDto(long productID, String productName, String description, boolean isInStock, int rating, LocalDate productDate, String brandName, String imageURL) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.isInStock = isInStock;
        this.rating = rating;
        this.productDate = productDate;
        this.brandName = brandName;
        this.imageURL = imageURL;
    }
}
