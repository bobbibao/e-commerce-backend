package com.vti.ecommerce.api.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.config.jwt.TokenProvider;
import com.vti.ecommerce.domains.entities.Cart;
import com.vti.ecommerce.services.CartService;
import com.vti.ecommerce.services.ProductService;
import com.vti.ecommerce.services.UserService;
import com.vti.ecommerce.services.dto.CartDto;

@RestController
@RequestMapping("/api/carts")
public class CartResource {

	private final CartService cartService;
	private final ProductService productService;
	private final UserService userService;

	@Autowired
    private TokenProvider tokenProvider;

	public CartResource(CartService cartService, ProductService productService, UserService userService) {
		this.cartService = cartService;
		this.productService = productService;
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<CartDto> getCart(Long productID, String token) {
		String userID = tokenProvider.getUsernameFromJWT(token);
		Cart cart = cartService.getCart(productID, userID);
		if (cart == null) {
			return ResponseEntity.notFound().build();
		}
		CartDto cartDto = new CartDto(cart);
		return ResponseEntity.ok(cartDto);
	}

	@PostMapping
	public ResponseEntity<String> addToCart(@RequestBody Long productID, String token, String selectedSize, int amount) {
		String userID = tokenProvider.getUsernameFromJWT(token);
		Cart cart = new Cart(productService.getProduct(productID), userService.getUser(userID), amount, selectedSize);
		boolean result = cartService.addToCart(cart);
		if (!result) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok("Cart added successfully");
	}

	@DeleteMapping
	public ResponseEntity<String> deleteCart(Long productID, String token) {
		String userID = tokenProvider.getUsernameFromJWT(token);
		boolean result = cartService.removeFromCart(productID, userID);
		if (!result) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok("Cart deleted successfully");
	}
	
}