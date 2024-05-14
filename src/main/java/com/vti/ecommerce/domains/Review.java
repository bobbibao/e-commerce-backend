package com.vti.ecommerce.domains;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@Getter @Setter @ToString
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_image")
	private String userImage;
	
	@Column(name = "location")
	private String location;

	@Column(name = "review_title")
	private String reviewTitle;
	
	@Column(name = "review_text")
	private String reviewText;

	@Column(name = "rating")
	private int rating;

	@Column(name = "date")
	private LocalDate date;
	
}
