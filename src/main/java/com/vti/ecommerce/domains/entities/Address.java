package com.vti.ecommerce.domains.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.ToString;

@Embeddable
@Data
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "address")
	private String address;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "city")
	private String city;

//	@Column(name = "country")
//	private String country;
	
	@Column(name = "zip_code")
	private int zipCode;

	@Override
	public String toString() {
		return "Address [address=" + address + ", state=" + state + ", city=" + city + ", zipCode=" + zipCode + "]";
	}
}
