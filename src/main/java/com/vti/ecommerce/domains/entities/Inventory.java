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
@Table(name = "inventories")
@Data
public class Inventory implements Serializable {
	private static final long serialVersionUID = -5286244336657823536L;

	@Id
	@Column(name = "inventory_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inventoryID;
	
	@Column(name = "quantity_in_stock")
	private int quantityInStock;
	
	@Column(name = "last_updated")
	private LocalDateTime lastUpdated;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
}
