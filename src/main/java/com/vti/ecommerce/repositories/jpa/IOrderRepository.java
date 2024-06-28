package com.vti.ecommerce.repositories.jpa;

import java.util.List;
import java.util.Map;

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

    //thống kê doanh thu theo tháng
    @Query(value = "SELECT MONTH(order_date) as month, SUM(total) as total FROM `order` WHERE YEAR(order_date) = ?1 GROUP BY MONTH(order_date)", nativeQuery = true)
    List<Map<String, Object>> revenueByMonth(int year);

    //thống kê doanh thu theo năm
    @Query(value = "SELECT YEAR(order_date) as year, SUM(total) as total FROM `order` GROUP BY YEAR(order_date)", nativeQuery = true)
    List<Map<String, Object>> revenueByYear();

	//thống kê top 4 giao dịch có doanh thu cao nhất
	@Query(value = "SELECT order_id, total FROM `order` ORDER BY total DESC LIMIT 4", nativeQuery = true)
	List<Map<String, Object>> top4Revenue();
}
