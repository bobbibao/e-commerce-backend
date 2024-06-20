package com.vti.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String>{
//	User findById(String userID);
	User findByEmail(String email);
//	boolean save(User user);
//	boolean deleteById(String userID);

	//thống kê số lượng nam và nữ
	// Map<String, Object> countUserByGender();
	

}
