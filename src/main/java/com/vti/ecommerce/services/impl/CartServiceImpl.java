package com.vti.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.Cart;
import com.vti.ecommerce.repositories.CartRepository;
import com.vti.ecommerce.services.CartService;
import com.vti.ecommerce.services.dto.CartDto;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private final CartRepository cartRepository;

	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	@Override
	public CartDto getCart(Long productID, String userID) {
		Cart cart = cartRepository.findById(productID, userID);
		if (cart == null) {
			return null;
		}
		CartDto cartDto = convertToCartDto(cart);
		return cartDto;
	}

	@Override
	public List<CartDto> getCartByUserId(String userID) {
		List<Cart> carts = cartRepository.findByUserId(userID);
		if (carts == null) {
			return null;
		}
		List<CartDto> cartDtos = null;
		for (Cart cart : carts) {
			cartDtos.add(convertToCartDto(cart));
		}
		return cartDtos;
	}

	@Override
	public boolean addToCart(CartDto cartDto) {
		boolean result = true;
		Cart cart = new Cart(cartDto.getAmount(), cartDto.getSelectedSize());

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

	public CartDto convertToCartDto(Cart cart) {
		return new CartDto(cart.getProduct().getProductID(), cart.getUser().getUserID(), cart.getAmount(), cart.getPrice(), cart.getSelectedSize());
	}
}
