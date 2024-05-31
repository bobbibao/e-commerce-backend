package com.vti.ecommerce.services.dto;

import com.vti.ecommerce.domains.entities.WishList;

public class WishListDto {
	private Long productId;
	private String userID;
    private int amount;
    private double price;
    private String selectedSize;

    public WishListDto() {
    }

    public WishListDto(Long productId, String userId, int amount, double price, String selectedSize) {
        this.productId = productId;
        this.amount = amount;
        this.price = price;
        this.selectedSize = selectedSize;
    }

    public WishListDto(WishList wishList) {
        this.productId = wishList.getProduct().getProductID();
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

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

	public String getUserId() {
		return userID;
	}

	public void setUserId(String userID) {
		this.userID = userID;
	}
    

}
