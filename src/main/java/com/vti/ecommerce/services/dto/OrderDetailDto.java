package com.vti.ecommerce.services.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class OrderDetailDto {
    private Long id;
    private ProductDto products;
    private int amount;
    private String selectedSize;


    public OrderDetailDto() {
    }

    public OrderDetailDto(Long id, ProductDto products, int amount, String selectedSize) {
        this.id = id;
        this.products = products;
        this.amount = amount;
        this.selectedSize = selectedSize;
    }

}
