package com.vti.ecommerce.repositories.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.domains.enumeration.ProductCategory;


@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
//	Product findById(Long productID);
//	ProductDto findById(String productID);
//	List<Product> findAll();
//	boolean save(Product product);
//	boolean deleteById(Long productID);
    List<Product> findByProductNameContaining(String search);
    List<Product> findByCategory(ProductCategory category);
    long countByCategory(ProductCategory category);

    //liệt kê số lượng sản phẩm theo từng loại sản phẩm
    @Query("SELECT p.category AS category, COUNT(p) AS count FROM Product p GROUP BY p.category")
    List<Map<String, Integer>> countProductByCategory();

    //Thống kê tôn kho/ đã bán của từng loại sản phẩm
    // @Query(value = "SELECT p.category AS category, SUM(od.quantity) AS count FROM Product p JOIN p.orderDetails od GROUP BY p.category")
    // @Query(value = "SELECT p.category AS category, SUM(od.quantity) AS count FROM Product p JOIN OrderDetail od ON p.id = od.product.id GROUP BY p.category")
    @Query(value = "SELECT p.category as category, SUM(p.inventory.quantitySold) as sold, SUM(p.inventory.quantityInStock) as stock FROM Product p GROUP BY p.category")
    List<Map<String, Object>> countProductSoldAndStockByCategory();

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
