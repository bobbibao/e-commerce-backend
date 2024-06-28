package com.vti.ecommerce.repositories.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.WishList;

@Repository
public interface IWishListRepository extends JpaRepository<WishList, Long>{
//	WishList findById(Long productID, String userID);
//	List<WishList> findByUserId(String userID);
//	boolean save(WishList wishList);
//	boolean deleteById(Long productID, String userID);

    @Query("SELECT w FROM WishList w WHERE w.user.userID = ?1")
    List<WishList> findByUser(String userID);

    @Query("DELETE FROM WishList w WHERE w.user.userID = ?1 AND w.product.productID = ?2")
    int deleteByUserAndProduct(String userID, Long productID);

    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM WishList w WHERE w.user.userID = ?1 AND w.product.productID = ?2")
    boolean findByUserAndProduct(String userID, long productID);
}
