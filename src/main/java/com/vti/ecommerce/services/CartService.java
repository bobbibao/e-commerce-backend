package com.vti.ecommerce.services;

import java.util.List;

import com.vti.ecommerce.services.dto.CartDto;


public interface CartService {
	CartDto getCart(Long productID, String userID);
	List<CartDto> getCartByUserId(String userID);
	boolean addToCart(CartDto cartDto);
	boolean removeFromCart(Long productID, String userID);
}
