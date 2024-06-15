package com.vti.ecommerce.services;

import java.util.List;

import com.vti.ecommerce.domains.entities.WishList;

public interface IWishListService {
//    WishList getWishList(String userID);
	List<WishList> getWishListByUserId(String userID);
	boolean addToWishList(WishList wishList);
	// boolean removeFromWishList(String userID, Long productID);
	boolean removeFromWishList(long id);

}
