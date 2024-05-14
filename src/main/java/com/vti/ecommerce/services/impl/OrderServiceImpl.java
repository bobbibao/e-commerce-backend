package com.vti.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.Order;
import com.vti.ecommerce.repositories.OrderRepository;
import com.vti.ecommerce.services.OrderService;
import com.vti.ecommerce.services.dto.OrderDto;


public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public OrderDto getOrder(Long orderID) {
        Order order = orderRepository.findById(orderID);
        if (order == null) {
            return null;
        }
        OrderDto orderDto = convertToOrderDto(order);
        return orderDto;
    }

    @Override
    public List<OrderDto> getOrderByUserId(String userID) {
        List<Order> orders = orderRepository.findByUserId(userID);
        if (orders == null) {
            return null;
        }
        List<OrderDto> orderDtos = null;
        for (Order order : orders) {
            orderDtos.add(convertToOrderDto(order));
        }
        return orderDtos;
    }

    @Override
    public boolean createOrder(OrderDto orderDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }

    @Override
    public boolean cancelOrder(Long orderID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelOrder'");
    }

    public OrderDto convertToOrderDto(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToOrderDto'");
    }
}
