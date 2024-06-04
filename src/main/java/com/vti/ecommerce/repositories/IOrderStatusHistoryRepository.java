package com.vti.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.ecommerce.domains.entities.OrderStatusHistory;

public interface IOrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Long>{

}
