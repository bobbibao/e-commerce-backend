package com.vti.ecommerce.services.dto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.vti.ecommerce.domains.entities.Order;
import com.vti.ecommerce.domains.entities.OrderStatusHistory;

import lombok.Data;
@Data
public class OrderDto {
    private String userId;
    private String orderStatus;
    private LocalDateTime orderDate;
    private double subtotal;
    private List<OrderDetailDto> cartItems;
    private String couponCode;
    private long id;

    public OrderDto() {
    }

    public OrderDto(String userId, String orderStatus, double subtotal, List<OrderDetailDto> cartItems, long id, String couponCode) {
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.subtotal = subtotal;
        this.cartItems = cartItems;
        this.id = id;
        this.couponCode = couponCode;
    }

    public OrderDto(Order order){
        this.userId = order.getUser().getUserID()+"";
        // List<String> orderStatuses = order.getOrderStatusHistory().stream().map(orderStatusHistory -> orderStatusHistory.getOrderStatus().toString()).collect(Collectors.toList());
        List<String> orderStatuses = order.getOrderStatusHistory().stream()
            .sorted(Comparator.comparing(OrderStatusHistory::getChangeAt)) // Sắp xếp theo thời gian
            .map(orderStatusHistory -> orderStatusHistory.getOrderStatus().toString())
            .collect(Collectors.toList());

        System.out.println(orderStatuses);
        this.orderStatus = orderStatuses.get(orderStatuses.size()-1);
        this.orderDate = order.getOrderDate();
        this.subtotal = order.getOrderTotal();
        this.id = order.getOrderID();
        this.cartItems = order.getOrderDetails().stream().map(orderDetail -> new OrderDetailDto(orderDetail)).collect(Collectors.toList());
        try{
            this.couponCode = order.getCoupon().getCode();
        }catch(Exception e){
            this.couponCode = null;
        }
    }


}
