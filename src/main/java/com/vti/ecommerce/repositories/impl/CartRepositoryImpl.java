package com.vti.ecommerce.repositories.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.Cart;
import com.vti.ecommerce.repositories.CartRepository;

@Repository
public class CartRepositoryImpl implements CartRepository{

	@Override
	public Cart findById(Long productID, String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> findByUserId(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart save(Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart deleteById(Long productID, String userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
