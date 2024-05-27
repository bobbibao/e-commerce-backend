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
@Data
@Table(name = "admin_activities")
public class AdminActivity implements Serializable{
	private static final long serialVersionUID = -2055500393695913938L;

	@Id
	@Column(name = "activity_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "activity_type")
	private String activityType;

	@Column(name = "activity_description")
	private String activityDescription;
	
	@Column(name = "activity_date")
	private LocalDateTime activityDate;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private User user;
}
