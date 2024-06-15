package com.vti.ecommerce.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.services.IOrderService;
import com.vti.ecommerce.services.dto.OrderDto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderResource {

	@Autowired
	private IOrderService orderService;

	public OrderResource(IOrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity getOrder() {
		return ResponseEntity.ok(orderService.getOrders());
	}

	// http://localhost:8080/orders/${localStorage.getItem("id")}
	@GetMapping("/user/{userID}")
	public ResponseEntity getOrderByUserId(@PathVariable String userID) { 
		return ResponseEntity.ok(orderService.getOrderByUserId(userID));
	}

	@GetMapping("/{orderID}")
	public ResponseEntity getOrderById(@PathVariable long orderID) {
		return ResponseEntity.ok(orderService.getOrderById(orderID));
	}

	@PostMapping
	public ResponseEntity createOrder(@RequestBody OrderDto orderDto) {
		return ResponseEntity.ok(orderService.addOrder(orderDto));
	}

	@PutMapping("/{orderID}/status/{updatedStatus}")
	public ResponseEntity updateOrderStatus(@PathVariable long orderID, @PathVariable String updatedStatus) {
		return ResponseEntity.ok(orderService.updateOrderStatus(orderID, updatedStatus));
	}

}
