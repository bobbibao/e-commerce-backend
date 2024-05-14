package com.vti.ecommerce.domains;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@Getter @Setter @ToString
public class Price implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "text", nullable = false)
	private String text;
	
	@Column(name = "value", nullable = false)
	private double value;
}
