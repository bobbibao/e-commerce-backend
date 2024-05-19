package com.vti.ecommerce.api.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.domains.Cart;
import com.vti.ecommerce.services.CartService;
import com.vti.ecommerce.services.dto.CartDto;

@RestController
@RequestMapping("/api/carts")
public class CartResource {

	private final CartService cartService;
	public CartResource(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping
	public ResponseEntity<CartDto> getCart(Long productID, String userID) {
		Cart cart = cartService.getCart(productID, userID);
		if (cart == null) {
			return ResponseEntity.notFound().build();
		}
		CartDto cartDto = new CartDto(cart);
		return ResponseEntity.ok(cartDto);
	}

	@PostMapping
	public ResponseEntity<CartDto> addToCart(CartDto cartDto) {
		Cart cart = new Cart(cartDto.getAmount(), cartDto.getSelectedSize());
		boolean result = cartService.addToCart(cart);
		if (!result) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(cartDto);
	}

	public ResponseEntity<CartDto> removeFromCart(Long productID, String userID) {
		boolean result = cartService.removeFromCart(productID, userID);
		if (!result) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<List<CartDto>> getCartByUserId(String userID) {
		List<Cart> carts = cartService.getCartByUserId(userID);
		if (carts == null) {
			return ResponseEntity.notFound().build();
		}
		List<CartDto> cartDtos = carts.stream().map(CartDto::new).collect(Collectors.toList());
		return ResponseEntity.ok(cartDtos);
	}
	
}