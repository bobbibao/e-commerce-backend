package com.vti.ecommerce.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "`orders`")
@NoArgsConstructor
@Getter @Setter @ToString
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long orderID;
	
	@Column(name = "order_total")
	private double orderTotal;
	
	@Column(name = "VAT")
	private double VAT;

	@Column(name = "shipping_cost")
	private double shippingCost;
	
	@Column(name = "order_date")
	private LocalDateTime orderDate;
	
	@Column(name = "shipping_address")
	private String shippingAddress;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrderStatusHistory> orderStatusHistory;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "vourcher_code", unique = true)
	private Voucher vourcher;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

//	public Order(OrderStatus orderStatus, LocalDateTime orderDate) {
//		super();
//		this.orderStatus = orderStatus;
//		this.orderTotal = getSubTotal();
//		this.orderDetails = new ArrayList<OrderDetail>();
//	}
	
	public double getSubTotal() {
		return this.orderDetails.stream()
				.mapToDouble(orderDetail -> orderDetail.getPrice())
				.sum();
	}
}
