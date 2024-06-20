package com.vti.ecommerce.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.Product;


@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
//	Product findById(Long productID);
//	ProductDto findById(String productID);
//	List<Product> findAll();
//	boolean save(Product product);
//	boolean deleteById(Long productID);
    List<Product> findByProductNameContaining(String search);

    //liệt kê số lượng tương ứng của tất cả loại sản phẩm tồn tại
    @Query(value = "SELECT category_id, COUNT(*) as count FROM product GROUP BY category_id", nativeQuery = true)
    List<Map<String, Object>> countProductByCategory();


}
