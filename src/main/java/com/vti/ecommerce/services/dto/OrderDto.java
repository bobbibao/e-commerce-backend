package com.vti.ecommerce.services.dto;

import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class OrderDto {
    private long id;
    private long userId;
    private Set<OrderDetailDto> orderDetailID;
    private double total;

    public OrderDto() {
    }

    public OrderDto(long id, long userId, Set<OrderDetailDto> orderDetailID, double total) {
        this.id = id;
        this.userId = userId;
        this.orderDetailID = orderDetailID;
        this.total = total;
    }
}
