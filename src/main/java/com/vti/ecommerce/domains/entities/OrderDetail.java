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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter @Setter
@NoArgsConstructor
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderDetailID;
	
	@Column(name = "amount")
	private int amount;

	@Column(name = "selected_size")
	private String selectedSize;

	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	public double getPrice() {
		try {
		return amount * product.getPrices().get(-1).getPriceValue();
		}catch (Exception e) {
			return 0;
		}
	}

	public OrderDetail(int amount, String selectedSize) {
		super();
		this.amount = amount;
		this.selectedSize = selectedSize;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailID=" + orderDetailID + ", amount=" + amount + ", selectedSize=" + selectedSize
				+ "]";
	}
	
	
}
