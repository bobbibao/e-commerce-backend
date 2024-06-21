package com.vti.ecommerce.services.dto;

import com.vti.ecommerce.domains.entities.OrderDetail;

import lombok.Data;

// {
//     //        "id": "205002072L",
//     //        "title": "ASOS DESIGN heeled chelsea boots in snake print faux leather",
//     //        "image": "images.asos-media.com/products/asos-design-heeled-chelsea-boots-in-snake-print-faux-leather/205002072-1-snakegrey",
//     //        "rating": 5,
//     //        "price": 99.99,
//     //        "brandName": "ASOS DESIGN",
//     //        "amount": 1,
//     //        "selectedSize": "L",
//     //        "isInWishList": false
//     //      }
@Data
public class OrderDetailDto {
    private long id;
    private String title;
    private String image;
    private int rating;
    private double price;
    private String brandName;
    private int amount;
    private String selectedSize;
    private boolean isInWishList;

    public OrderDetailDto() {
    }

    public OrderDetailDto(long id, String title, String image, int rating, double price, String brandName, int amount, String selectedSize, boolean isInWishList) {
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

    // public static OrderDetailDto fromProductDto(ProductDto productDto) {
    //     return new OrderDetailDto(productDto.getId(), productDto.getTitle(), productDto.getImage(), productDto.getRating(), productDto.getPrice(), productDto.getBrandName(), 1, productDto.getSelectedSize(), false);
    // }

    public OrderDetailDto(OrderDetail orderDetail){
        this.id = orderDetail.getProduct().getProductID();
        this.title = orderDetail.getProduct().getProductName();
        this.image = orderDetail.getProduct().getImageURL();
        this.rating = orderDetail.getProduct().getRating();
        this.price = orderDetail.getProduct().getPrices().get(0).getPriceValue();
        this.brandName = orderDetail.getProduct().getBrandName();
        this.amount = orderDetail.getAmount();
        this.selectedSize = orderDetail.getSelectedSize();
        this.isInWishList = false;
    }
}
