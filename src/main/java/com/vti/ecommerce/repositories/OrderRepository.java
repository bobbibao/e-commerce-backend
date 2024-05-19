package com.vti.ecommerce.repositories;

import java.util.List;

import com.vti.ecommerce.domains.Order;


public interface OrderRepository {
	Order findById(Long orderID);
	List<Order> findByUserId(String userID);
	boolean save(Order order);
	boolean deleteById(Long orderID);
	
}
