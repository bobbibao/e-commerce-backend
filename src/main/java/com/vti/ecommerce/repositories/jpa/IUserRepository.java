package com.vti.ecommerce.repositories.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.User;
@Repository
public interface IUserRepository extends JpaRepository<User, String>{
//	User findById(String userID);
	User findByEmail(String email);
//	boolean save(User user);
//	boolean deleteById(String userID);

	//thống kê số lượng nam và nữ
    @Query("SELECT u.gender AS gender, COUNT(u) AS count FROM User u GROUP BY u.gender")
    List<Map<String, Integer>> countUserByGender();

    long countByGender(String gender);
}
