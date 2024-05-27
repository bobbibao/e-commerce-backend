package com.vti.ecommerce.domains.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vourchers")
@Data
public class Voucher implements Serializable{
	private static final long serialVersionUID = -6928601443624105845L;

	@Id
	@Column(name = "vourcher_code")
	private String vourcherCode;

	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Column(name = "voucher_title")
	private String voucherTitle;
	
	@Column(name = "voucher_description")
	private String voucherDescription;

	@Column(name = "discount_value")
	private String discountValue;

	@Column(name = "is_active")
	private boolean isActive;
}
