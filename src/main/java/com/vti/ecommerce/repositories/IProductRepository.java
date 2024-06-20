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

    //sold:
    //liệt kê số lượng sản phẩm đã bán được theo từng id sản phẩm
    @Query(value = "SELECT product_id, SUM(quantity) as count FROM order_detail GROUP BY product_id", nativeQuery = true)
    List<Map<String, Object>> countProductSold();

    //số lượng tồn kho, tổng số lượng sản phẩm đã bán, tổng số lượng sản phẩm
    //số lượng tồn kho (quantity_in_stock) của :product_id
    @Query(value = "SELECT quantity_in_stock FROM inventories WHERE product_id = :product_id", nativeQuery = true)
    int countProductInStock(Long product_id);

    //tổng số lượng sản phẩm đã bán (quantity_sold) của :product_id
    @Query(value = "SELECT quantity_in_stock FROM inventories WHERE product_id = :product_id", nativeQuery = true)
    int countProductSold(Long product_id);
}
