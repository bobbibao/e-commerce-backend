package com.vti.ecommerce.services;

import java.util.List;

import com.vti.ecommerce.services.dto.OrderDto;

public interface OrderService {
	OrderDto getOrder(Long orderID);
	List<OrderDto> getOrderByUserId(String userID);
	boolean createOrder(OrderDto orderDto);
	boolean cancelOrder(Long orderID);
}
