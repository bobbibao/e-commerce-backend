package com.vti.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.Cart;
import com.vti.ecommerce.repositories.CartRepository;
import com.vti.ecommerce.services.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private final CartRepository cartRepository;

	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	@Override
	public Cart getCart(Long productID, String userID) {
		Cart cart = cartRepository.findById(productID, userID);
		if (cart == null) {
			return null;
		}
		return cart;
	}

	@Override
	public List<Cart> getCartByUserId(String userID) {
		List<Cart> carts = cartRepository.findByUserId(userID);
		if (carts == null) {
			return null;
		}
		return carts;
	}

	@Override
	public boolean addToCart(Cart cart) {
		boolean result = true;

		if (cartRepository.save(cart) == null) {
			result = false;
		}

		return result;
	}

	@Override
	public boolean removeFromCart(Long productID, String userID) {
		boolean result = true;
		Cart cart = cartRepository.deleteById(productID, userID);
		if (cart == null) {
			result = false;
		}
		return result;
	}

	// public Cart convertToCart(Cart cart) {
	// 	return new Cart(cart.getAmount(), cart.getSelectedSize());
	// }
}
