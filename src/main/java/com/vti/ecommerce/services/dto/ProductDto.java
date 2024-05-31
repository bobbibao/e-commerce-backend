package com.vti.ecommerce.services.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.domains.enumeration.ProductCategory;
import com.vti.ecommerce.domains.enumeration.ProductGender;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProductDto {
	private long productID;
    private String productName;
    private String description;
    private boolean isInStock;
    private int rating;
    private String brandName;
    private String imageURL;
    private double price;
    // Constructors, Getters, and Setters

    public ProductDto() {
    }
    public ProductDto(long productID, String productName, String description, boolean isInStock, int rating, String brandName, String imageURL, double price) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.isInStock = isInStock;
        this.rating = rating;
        this.brandName = brandName;
        this.imageURL = imageURL;
        this.price = price;
    }
}
