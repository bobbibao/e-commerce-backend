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
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "roles")
@NoArgsConstructor
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

	public Role(String roleName) {
		if (roleName == null || roleName.isBlank()) {
			throw new IllegalArgumentException("Role name must not be null or empty");
		}
		if (roleName.length() < 3 || roleName.length() > 50) {
			throw new IllegalArgumentException("Role name must be between 3 and 50 characters");
		}
		if (!roleName.matches("^[a-zA-Z0-9_]+$")) {
				throw new IllegalArgumentException("Role name must contain only letters, numbers and underscores");
			}
		this.roleName = roleName;
		this.roleID = ("Admin".equals(roleName) ? 1 : 2);
	}
}