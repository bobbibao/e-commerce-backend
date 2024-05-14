package com.vti.ecommerce.services.dto;

import com.vti.ecommerce.domains.Cart;

public class CartDto {
    private Long productId;
    private String userId;
    private int amount;
    private double price;
    private String selectedSize;

    public CartDto(Long productId, String userId, int amount, double price, String selectedSize) {
        this.productId = productId;
        this.userId = userId;
        this.amount = amount;
        this.price = price;
        this.selectedSize = selectedSize;
    }

    public CartDto(Cart cart) {
        //TODO Auto-generated constructor stub
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAmount'");
    }

    public String getSelectedSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSelectedSize'");
    }

}
