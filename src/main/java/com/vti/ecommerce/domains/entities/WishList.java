package com.vti.ecommerce.domains.entities;


import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "wish_lists")
@NoArgsConstructor
@Getter @Setter @ToString
public class WishList implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "wish_list_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long wishListID;
	
	@Column(name = "amount")
	private int amount;

	@Column(name = "selected_size")
	private String selectedSize;

	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
	private Product product;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public WishList(int amount, String selectedSize) {
		super();
		this.amount = amount;
		this.selectedSize = selectedSize;
	}
	
	public WishList(Long productId, String userId, String selectedSize, int amount) {
		this.amount = amount;
		this.selectedSize = selectedSize;
		this.product = new Product(productId);
	}
	public double getPrice() {
		return amount * product.getPrices().get(-1).getPriceValue();
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WishList other = (WishList) obj;
		return Objects.equals(product, other.product) && Objects.equals(user, other.user);
	}
	
	
}
