package com.vti.ecommerce.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.WishList;
import com.vti.ecommerce.repositories.IWishListRepository;
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
//	@Override
//	public List<WishList> getWishListByUserId(String userID) {
//		return wishListRepository.findByUserId(userID);
//	}
//
//	@Override
//	public boolean addToWishList(WishList wishList) {
//		return wishListRepository.save(wishList);
//	}
//
//	@Override
//	public boolean removeFromWishList(Long productID, String userID) {
//		return wishListRepository.deleteById(productID, userID);
//	}


}
