package com.vti.ecommerce.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment implements Serializable{
	private static final long serialVersionUID = -5734665941109418705L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private long paymentID;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "payment_status")
	private String paymentStatus;

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;
}
