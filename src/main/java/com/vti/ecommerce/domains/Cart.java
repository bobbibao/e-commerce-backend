package com.vti.ecommerce.domains;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "cart")
@NoArgsConstructor
@Getter @Setter @ToString
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "amount")
	private int amount;

	@Column(name = "price")
	private double price;

	@Column(name = "selected_size")
	private String selectedSize;

	@Id
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@Id
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	

	public Cart(int amount, String selectedSize) {
		super();
		this.amount = amount;
		this.selectedSize = selectedSize;
		this.price = getPrice();
	}
	
	public double getPrice() {
		return amount * product.getPrices().get(-1).getValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(product, other.product) && Objects.equals(user, other.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, user);
	}
}
