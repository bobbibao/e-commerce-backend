package com.vti.ecommerce.domains.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vti.ecommerce.domains.enumeration.OrderStatus;
import com.vti.ecommerce.services.dto.OrderDetailDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
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
@NamedQueries({
	@NamedQuery(name = "Order.getOrderByUserId", query = "SELECT o FROM Order o WHERE o.user.userID = :userID"),
	@NamedQuery(name = "Order.getOrderByOrderID", query = "SELECT o FROM Order o WHERE o.orderID = :orderID"),
	@NamedQuery(name = "Order.getOrderByOrderDate", query = "SELECT o FROM Order o WHERE o.orderDate = :orderDate"),
	@NamedQuery(name = "Order.addOrder", query = "INSERT INTO Order o (o.orderTotal, o.VAT, o.shippingCost, o.orderDate, o.shippingAddress, o.user) VALUES (:orderTotal, :VAT, :shippingCost, :orderDate, :shippingAddress, :user)"),
	@NamedQuery(name = "Order.getOrderByOrderStatus", query = "SELECT o FROM Order o INNER JOIN OrderStatusHistory osh ON o.orderID = osh.order.orderID WHERE osh.orderStatus = :orderStatus"),
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	
	@JsonManagedReference
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrderStatusHistory> orderStatusHistory;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_code", unique = true)
	private Coupon coupon;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails;
	
	@JsonBackReference
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

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", orderTotal=" + orderTotal + ", VAT=" + VAT + ", shippingCost="
				+ shippingCost + ", orderDate=" + orderDate ;
	}

	

}
