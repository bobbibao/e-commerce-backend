package com.vti.ecommerce.repositories;

import java.util.List;

import com.vti.ecommerce.domains.entities.Cart;


public interface CartRepository {
	Cart findById(Long productID, String userID);
	List<Cart> findByUserId(String userID);
	Cart save(Cart cart);
	Cart deleteById(Long productID, String userID);
	
}
