package com.vti.ecommerce.domains.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vti.ecommerce.domains.enumeration.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity
@Table(name = "order_status_histories")
@Getter @Setter @NoArgsConstructor
public class OrderStatusHistory {
	@Id
	@Column(name = "status_history_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long statusHistoryID;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "order_status")
	private OrderStatus orderStatus;
	
	@Column(name = "change_at")
	private LocalDateTime changeAt;
	
	@OneToOne
	@JoinColumn(name = "change_by", referencedColumnName = "user_id", unique = false)
	private User changeBy;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	public OrderStatusHistory(OrderStatus orderStatus, LocalDateTime changeAt, User changeBy, Order order) {
		super();
		this.orderStatus = orderStatus;
		this.changeAt = changeAt;
		this.changeBy = changeBy;
		this.order = order;
	}

	public OrderStatusHistory(OrderStatus orderStatus, LocalDateTime changeAt) {
		this.orderStatus = orderStatus;
		this.changeAt = changeAt;
	}

	@Override
	public String toString() {
		return "OrderStatusHistory [statusHistoryID=" + statusHistoryID + ", orderStatus=" + orderStatus + ", changeAt="
				+ changeAt + ", changeBy=" + changeBy + "]";
	}

	

}
