package com.vti.ecommerce.domains.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "user_id")
	private String userID;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "gender")
	private String gender;
	
	@Column(name = "registration_date")
	private LocalDate registrationDate;
	
	@Embedded
	private Address address;

	@Column(name = "password_hash")
	private String passwordHash;

	@Column(name = "password_reset_token")
	private String passwordResetToken;

	@Column(name = "password_reset_expiration")
	private String passwordResetExpiration;
	
	@Column(name = "is_active")
	private boolean isActive;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserRole> userRoles;

	@Column(name = "user_image")
	private String userImage;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<WishList> userWishLists;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Order> orders;

	@JsonManagedReference
	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<CustomerReview> customerReview;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AdminActivity> adminActivities;
	
}

