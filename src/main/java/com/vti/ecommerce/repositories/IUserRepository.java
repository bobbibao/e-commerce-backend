package com.vti.ecommerce.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.Order;
import com.vti.ecommerce.domains.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String>{
//	User findById(String userID);
	User findByEmail(String email);
//	boolean save(User user);
//	boolean deleteById(String userID);
	
}
