package com.vti.ecommerce.services.dto;

import com.vti.ecommerce.domains.WishList;

public class WishListDto {
	private Long productId;
    private String userId;
    private int amount;
    private double price;
    private String selectedSize;

    public WishListDto() {
    }

    public WishListDto(Long productId, String userId, int amount, double price, String selectedSize) {
        this.productId = productId;
        this.userId = userId;
        this.amount = amount;
        this.price = price;
        this.selectedSize = selectedSize;
    }

    public WishListDto(WishList wishList) {
        this.productId = wishList.getProduct().getProductID();
        this.userId = wishList.getUser().getUserID();
        this.amount = wishList.getAmount();
        this.price = wishList.getPrice();
        this.selectedSize = wishList.getSelectedSize();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public String getSelectedSize() {
        return selectedSize;
    }
    

}
