package com.vti.ecommerce.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.OrderStatusHistory;

@Repository
public interface IOrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Long>{
    @Query("SELECT o FROM OrderStatusHistory o WHERE o.order.orderID = :orderId")
    Set<OrderStatusHistory> findByOrderId(Long orderId);
}
