package com.vti.ecommerce.repositories;

import com.vti.ecommerce.domains.User;

public interface UserRepository {
	User findById(String userID);
	User findByUserName(String userName);
	boolean save(User user);
	boolean deleteById(String userID);
}
