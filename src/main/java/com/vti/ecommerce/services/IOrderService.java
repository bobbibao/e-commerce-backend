package com.vti.ecommerce.services;

import java.util.List;
import java.util.Map;

import com.vti.ecommerce.services.dto.OrderDto;

public interface IOrderService {
//	OrderDto getOrder(Long orderID);
//	List<OrderDto> getOrderByUserId(String userID);
//	boolean createOrder(OrderDto orderDto);
//	boolean cancelOrder(Long orderID);

    boolean addOrder(OrderDto orderDto);
    boolean createOrder(String email, Map<String, Object> order);
    List<OrderDto> getOrders();
    OrderDto getOrder(Long orderID);
    boolean removeOrder(Long orderID);
    boolean updateOrder(Long userID, Long orderID, Map<String, Object> order);
    List<OrderDto> getOrderByUserId(String userID);
    boolean cancelOrder(Long orderID);
    boolean confirmOrder(Long orderID);
    boolean updateOrderStatus(Long orderID, String status);
    OrderDto getOrderById(long orderID);
}
