package com.vti.ecommerce.domains.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "suppliers")
@Data
public class Supplier implements Serializable{
	
	private static final long serialVersionUID = 1032170653070278525L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
	private int supplierID;
	
	@Column(name = "supplier_name")
	private String supplierName;
	
	@Column(name = "contact_name")
	private String contactName;
	
	@Column(name = "contact_phone")
	private String contactPhone;
	
	@Column(name = "contact_email")
	private String contactEmail;

//	@Column(name = "address")
	@Embedded
	private Address address;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Product> products;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RestockHistory> restockHistorys;
}
