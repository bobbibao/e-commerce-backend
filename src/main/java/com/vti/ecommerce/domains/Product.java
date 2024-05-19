package com.vti.ecommerce.domains;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.vti.ecommerce.domains.enumeration.ProductCategory;
import com.vti.ecommerce.domains.enumeration.ProductGender;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter @Setter @ToString
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productID;
	
	@Column(name = "product_code")
	private String productCode;
	

	@Column(name = "product_name", nullable = false)
	private String productName;
	

	@Column(name = "description")
	private String description;
	

	@Column(name = "is_in_stock")
	private boolean isInStock;
	

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private ProductGender gender;
	

	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private ProductCategory category;
	

	@Column(name = "available_size")
	private Set<String> availableSize;
	

	@Column(name = "rating")
	private int rating;
	

	@Column(name = "total_review_count")
	private int totalReviewCount;

	@Column(name = "product_date")
	private LocalDate productDate;
	
	@Column(name = "brand_name")
	private String brandName;
	

	@Column(name = "image_url")
	private String imageURL;
	

	@Column(name = "additional_image_urls")
	private List<String> additionalImageURLs;
	

	@ElementCollection
	@CollectionTable(name = "prices", joinColumns = @JoinColumn(name = "product_id"))
	@Column(name = "prices", nullable = false)
	@Embedded
	private List<Price> prices;
	


	@ElementCollection
	@CollectionTable(name = "reviews", joinColumns = @JoinColumn(name = "product_id"))
	@Column(name = "reviews", nullable = false)
	@Embedded
	private List<Review> reviews;

}
