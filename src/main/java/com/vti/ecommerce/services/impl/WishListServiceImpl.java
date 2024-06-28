package com.vti.ecommerce.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.WishList;
import com.vti.ecommerce.repositories.jpa.IWishListRepository;
import com.vti.ecommerce.services.IWishListService;

@Service
public class WishListServiceImpl implements IWishListService{
	private final IWishListRepository wishListRepository;

	public WishListServiceImpl(IWishListRepository wishListRepository) {
		this.wishListRepository = wishListRepository;
	}

//	@Override
//	public WishList getWishList(Long productID, String userID) {
//		return wishListRepository.findById(productID, userID);
//	}
//
	@Override
	public List<WishList> getWishListByUserId(String userID) {
		return wishListRepository.findByUser(userID);
	}
//
	@Override
	public boolean addToWishList(WishList wishList) {
		boolean wishListExist = wishListRepository.findByUserAndProduct(wishList.getUser().getUserID(), wishList.getProduct().getProductID());
		if (wishListExist) {
			return false;
		}
		wishListRepository.save(wishList);
		return true;
	}

    // @Override
    // public boolean removeFromWishList(String userID, Long productID) {
	// 	return wishListRepository.deleteByUserAndProduct(userID, productID) > 0;
    // }

	@Override
	public boolean removeFromWishList(long id) {
		wishListRepository.deleteById(id);
		return true;
	}
}
