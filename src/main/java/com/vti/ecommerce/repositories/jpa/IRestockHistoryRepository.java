package com.vti.ecommerce.repositories.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.RestockHistory;

@Repository
public interface IRestockHistoryRepository extends JpaRepository<RestockHistory, Long>{
//	RestockHistory findById(Long restockHistoryID);
//	List<RestockHistory> findByProductId(Long productID);
//	RestockHistory save(RestockHistory restockHistory);
//	RestockHistory deleteById(Long restockHistoryID);
	
}
