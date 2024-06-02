package com.vti.ecommerce.api.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.config.jwt.TokenProvider;
import com.vti.ecommerce.domains.entities.Order;
import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.services.IOrderService;
import com.vti.ecommerce.services.IUserService;
import com.vti.ecommerce.services.dto.OrderDto;


@RestController
@RequestMapping("/api/users")
public class UserResource {

	@Autowired
	private IUserService userService;

	@Autowired
	private IOrderService orderService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

	@PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody String username){
		String token = tokenProvider.generateToken(username);
		return ResponseEntity.ok(token);
	}
	
	@GetMapping
	public String a() {
		String token = tokenProvider.generateToken("bobbibao");
		System.out.println("asd" + token);
		return token;
	}

//	@GetMapping("/{id}")
//	public ResponseEntity getResponseEntity(@PathVariable String id) {
//		User user = userService.getUser(id);
//		return ResponseEntity.ok(user);
//	}
	
	@GetMapping("/shopping-cart")
	public ResponseEntity getOrders() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Map<String, Object> response = new HashMap<>();
		String email = authentication.getName();
		List<OrderDto> orders = userService.getOrdersByEmail(email);
		if (orders.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		response.put("orders", orders.size());
		response.put("data", orders);
		System.out.println(response);
		return ResponseEntity.ok(orders);
	}

	@PostMapping("/shopping-cart")
	public ResponseEntity createOrder(@RequestBody Map<String, Object> order) {
		Map<String, Object> response = new HashMap<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		System.out.println("create order: " + order + email);
		response.put("success", orderService.createOrder(email, order));
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{id}/shopping-cart/{orderId}")
	public ResponseEntity updateOrder(@PathVariable long id, @PathVariable long orderId, @RequestBody Map<String, Object> order) {
		Map<String, Object> response = new HashMap<>();
		response.put("success", orderService.updateOrder(id, orderId, order));
		return ResponseEntity.ok(response);
	}
}
