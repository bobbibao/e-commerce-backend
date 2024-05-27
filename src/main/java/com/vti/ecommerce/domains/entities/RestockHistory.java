package com.vti.ecommerce.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "restock_histories")
@Data
public class RestockHistory implements Serializable{
	private static final long serialVersionUID = -5286244336657823536L;

	@Id
	@Column(name = "restock_history_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long restockHistoryID;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "restock_date")
	private LocalDateTime restockDate;
	
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
	private Product product;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
	private Supplier supplier;
}