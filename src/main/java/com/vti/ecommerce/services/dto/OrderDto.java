package com.vti.ecommerce.services.dto;

import java.time.LocalDate;
import java.util.List;

import com.vti.ecommerce.domains.enumeration.OrderStatus;

public class OrderDto {
	private Long orderId;
    private String userId;
    private LocalDate orderDate;
    private double subTotal;
    private OrderStatus orderStatus;
    private List<OrderDetailDto> orderDetails;
}
