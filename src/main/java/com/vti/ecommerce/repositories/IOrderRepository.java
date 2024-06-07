package com.vti.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.Order;
import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.domains.enumeration.OrderStatus;


@Repository
public interface IOrderRepository extends JpaRepository<Order, Long>{
	List<Order> findByUser(User user);
	
	@Query("SELECT o FROM Order o JOIN o.orderStatusHistory h WHERE o.user.email = :email AND h.orderStatus = :orderStatus")
	Order findByUserEmailAndStatus(String email, OrderStatus orderStatus);
}
