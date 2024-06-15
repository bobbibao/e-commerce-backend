package com.vti.ecommerce.api.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.config.jwt.TokenProvider;
import com.vti.ecommerce.services.IOrderService;
import com.vti.ecommerce.services.IUserService;
import com.vti.ecommerce.services.dto.OrderDto;
import com.vti.ecommerce.services.dto.UserDto;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
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
	
	// @GetMapping
	// public String a() {
	// 	String token = tokenProvider.generateToken("bobbibao");
	// 	System.out.println("asd" + token);
	// 	return token;
	// }

	@GetMapping
	public ResponseEntity getAll() throws Exception {
		List<UserDto> users = userService.getAll();
		return ResponseEntity.ok(users);
	}
	
	@PostMapping
	public ResponseEntity createUser(@RequestBody UserDto user) {
		Map<String, Object> response = new HashMap<>();
		response.put("success", userService.insert(user));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity getResponseEntity(@PathVariable String id) {
		Optional<UserDto> user = userService.getById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity updateUser(@PathVariable String id, @RequestBody UserDto user) {
		Map<String, Object> response = new HashMap<>();
		response.put("success", userService.update(user));
		return ResponseEntity.ok(response);
	}

	@PostMapping("/change-password")
	public ResponseEntity changePassword(@RequestBody Map<String, Object> passwords) {
		Map<String, Object> response = new HashMap<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		System.out.println(email);
		response.put("success", userService.changePassword(email, (String) passwords.get("oldPassword"), (String) passwords.get("newPassword")));
		return ResponseEntity.ok(response);
	}
	
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

	@PutMapping("/{id}/role")
	public ResponseEntity updateRole(@PathVariable String id, @RequestBody Map<String, Object> role) {
		System.out.println(role);
		Map<String, Object> response = new HashMap<>();
		response.put("success", userService.updateUserRole(id, (String) role.get("role")));
		return ResponseEntity.ok(response);
	}

	
}
