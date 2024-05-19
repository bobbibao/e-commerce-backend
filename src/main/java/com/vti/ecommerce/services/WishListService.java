package com.vti.ecommerce.services;

import java.util.List;

import com.vti.ecommerce.domains.WishList;

public interface WishListService {
    WishList getWishList(Long productID, String userID);
	List<WishList> getWishListByUserId(String userID);
	boolean addToWishList(WishList wishList);
	boolean removeFromWishList(Long productID, String userID);

}
