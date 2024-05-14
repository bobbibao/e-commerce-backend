package com.vti.ecommerce.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public CartDto hello() {
		return cartService.getCart(1l, "2");
	}
}
