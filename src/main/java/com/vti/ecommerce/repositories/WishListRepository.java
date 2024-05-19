package com.vti.ecommerce.repositories;

import java.util.List;

import com.vti.ecommerce.domains.WishList;


public interface WishListRepository {
	WishList findById(Long productID, String userID);
	List<WishList> findByUserId(String userID);
	boolean save(WishList wishList);
	boolean deleteById(Long productID, String userID);
	
}
