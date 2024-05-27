package com.vti.ecommerce.services;

import java.util.List;

import com.vti.ecommerce.domains.entities.Cart;


public interface CartService {
	Cart getCart(Long productID, String userID);
	List<Cart> getCartByUserId(String userID);
	boolean addToCart(Cart Cart);
	boolean removeFromCart(Long productID, String userID);
}
