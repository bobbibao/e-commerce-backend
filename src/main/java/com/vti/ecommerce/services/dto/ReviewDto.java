package com.vti.ecommerce.services.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReviewDto {
    private String username;
    private String userImage;
    private String location;
    private int rating;
    private String date;
    private String reviewTitle;
    private String reviewText;

    public ReviewDto(String username, String userImage, String location, int rating, String date, String reviewTitle, String reviewText) {
        this.username = username;
        this.userImage = userImage;
        this.location = location;
        this.rating = rating;
        this.date = date;
        this.reviewTitle = reviewTitle;
        this.reviewText = reviewText;
    }

    public ReviewDto() {
    }

}
