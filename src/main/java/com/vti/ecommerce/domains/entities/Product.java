package com.vti.ecommerce.domains.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vti.ecommerce.domains.enumeration.ProductCategory;
import com.vti.ecommerce.domains.enumeration.ProductGender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 5902886343031436632L;

	@Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productID;

    @Column(name = "product_code", unique = true)
    private String productCode;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_in_stock")
    private boolean isInStock;
    
    @Column(name = "rating")
    private int rating;

    @Column(name = "created_at")
    private LocalDate createAt;
    
    @Column(name = "updated_at")
    private LocalDate updateAt;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "image_url")
    private String imageURL;
    
    @Column(name = "status")
    private String status;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;
    
//    @Column(name = "import_price")
//    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private ProductGender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ProductCategory category;

    @JsonManagedReference
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "available_sizes", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "size")
    private Set<String> availableSize;

    @JsonManagedReference
    @ElementCollection
    @CollectionTable(name = "additional_image_urls", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "url")
    private List<String> additionalImageURLs;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductPrice> prices;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerReview> customerReviews;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RestockHistory> restockHistory;
    
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    
    public Product(Long productId) {
        this.productID = productId;
    }

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productCode=" + productCode + ", productName=" + productName
				+ ", description=" + description + ", isInStock=" + isInStock + ", rating=" + rating + ", createAt="
				+ createAt + ", updateAt=" + updateAt + ", brandName=" + brandName + ", imageURL=" + imageURL
				+ ", status=" + status + ", gender=" + gender + ", category=" + category + "]";
	}
}
