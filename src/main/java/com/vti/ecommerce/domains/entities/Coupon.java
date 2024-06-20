package com.vti.ecommerce.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "coupon_codes")
@Data
public class Coupon implements Serializable{
	private static final long serialVersionUID = -6928601443624105845L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_id")
	private long couponID;

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "expiration_date")
	private LocalDateTime expirationDate;
	
	@Column(name = "voucher_title")
	private String voucherTitle;
	
	@Column(name = "voucher_description")
	private String voucherDescription;

	@Column(name = "discount_value")
	private double discountValue;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "usage_limit")
	private int usageLimit;

	@Column(name = "usage_amount")
	private int usageAmount;
}
