package com.vti.ecommerce.domains.entities;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role implements Serializable{
	private static final long serialVersionUID = -9029763831848189158L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int roleID;

	@Column(name = "role_name", unique = true)
	private String roleName;
	
	@JsonBackReference
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserRole> userRoles;
}
