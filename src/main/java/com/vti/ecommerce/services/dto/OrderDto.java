package com.vti.ecommerce.services.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.vti.ecommerce.domains.entities.Order;

import lombok.Data;

//{
//    "userId": "UIJrEcYfkKo3wKOni2t9Q",
//    "orderStatus": "in progress",
//    "subtotal": 306.99,
//    "cartItems": [
//      {
//        "id": "204453531S",
//        "title": "AAPE By A Bathing Ape baseball t-shirt in black",
//        "image": "images.asos-media.com/products/aape-by-a-bathing-ape-baseball-t-shirt-in-black/204453531-1-black",
//        "rating": 4,
//        "price": 69,
//        "brandName": "AAPE BY A BATHING APE®",
//        "amount": 3,
//        "selectedSize": "S",
//        "isInWishList": false
//      },
//      {
//        "id": "205002072L",
//        "title": "ASOS DESIGN heeled chelsea boots in snake print faux leather",
//        "image": "images.asos-media.com/products/asos-design-heeled-chelsea-boots-in-snake-print-faux-leather/205002072-1-snakegrey",
//        "rating": 5,
//        "price": 99.99,
//        "brandName": "ASOS DESIGN",
//        "amount": 1,
//        "selectedSize": "L",
//        "isInWishList": false
//      }
//    ],
//    "id": 1
//  },
@Data
public class OrderDto {
    private String userId;
    private String orderStatus;
    private LocalDateTime orderDate;
    private double subtotal;
    private List<OrderDetailDto> cartItems;
    private long id;

    public OrderDto() {
    }

    public OrderDto(String userId, String orderStatus, double subtotal, List<OrderDetailDto> cartItems, long id) {
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.subtotal = subtotal;
        this.cartItems = cartItems;
        this.id = id;
    }

    public OrderDto(Order order){
        this.userId = order.getUser().getUserID()+"";
        var orderStatuses = order.getOrderStatusHistory().stream().map(orderStatusHistory -> orderStatusHistory.getOrderStatus().toString()).collect(Collectors.toList());
        this.orderStatus = orderStatuses.get(orderStatuses.size()-1);
        this.orderDate = order.getOrderDate();
        this.subtotal = order.getOrderTotal();
        this.id = order.getOrderID();
        this.cartItems = order.getOrderDetails().stream().map(orderDetail -> new OrderDetailDto(orderDetail)).collect(Collectors.toList());
    }


}
