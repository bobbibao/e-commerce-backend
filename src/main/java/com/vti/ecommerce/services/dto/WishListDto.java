package com.vti.ecommerce.services.dto;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.domains.entities.WishList;

import lombok.Data;

// {
//     "id": 204453531,
//     "title": "AAPE By A Bathing Ape baseball t-shirt in black",
//     "image": "images.asos-media.com/products/aape-by-a-bathing-ape-baseball-t-shirt-in-black/204453531-1-black",
//     "rating": 4,
//     "price": 69,
//     "brandName": "AAPE BY A BATHING APE®",
//     "amount": 1,
//     "selectedSize": "XS",
//     "isInWishList": false
//   },
@Data
public class WishListDto {
    private long id;
    private String title;
    private String image;
    private int rating;
    private double price;
    private String brandName;
    private int amount;
    private String selectedSize;
    private boolean isInWishList;

    public WishListDto() {
    }

    public WishListDto(long id, String title, String image, int rating, double price, String brandName, int amount,
            String selectedSize, boolean isInWishList) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.price = price;
        this.brandName = brandName;
        this.amount = amount;
        this.selectedSize = selectedSize;
        this.isInWishList = isInWishList;
    }

    public WishListDto(WishList wishList) {
        this.id = wishList.getWishListID();
        this.title = wishList.getProduct().getProductName();
        this.image = wishList.getProduct().getImageURL();
        this.rating = wishList.getProduct().getRating();
        this.price = 1000;
        this.brandName = wishList.getProduct().getBrandName();
        this.amount = wishList.getAmount();
        this.selectedSize = wishList.getSelectedSize();
        this.isInWishList = true;

    }

    public WishList convertToEntity() {
        WishList wishList = new WishList();
        Product product = new Product(this.id);
        product.setProductName(this.title);
        wishList.setWishListID(this.id);
        wishList.setAmount(this.amount);
        wishList.setSelectedSize(this.selectedSize);
        wishList.setProduct(product);
        
        return wishList;
    }
}
