package com.vti.ecommerce.api.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.domains.entities.WishList;
import com.vti.ecommerce.services.IProductService;
import com.vti.ecommerce.services.IUserService;
import com.vti.ecommerce.services.IWishListService;
import com.vti.ecommerce.services.dto.WishListDto;

@RestController
@RequestMapping("/api/wish-list")
public class WishListResource {

	private final IWishListService wishListService;
	private final IProductService productService;
	private final IUserService userService;
	
	public WishListResource(IWishListService wishListService, IProductService productService, IUserService userService) {
		this.wishListService = wishListService;
		this.productService = productService;
		this.userService = userService;
	}

//	@GetMapping()
//	public List<WishListDto> getWishList(String userID) {
//		List<WishList> wishLists = wishListService.getWishListByUserId(userID);
//		return wishLists.stream().map(WishListDto::new).collect(Collectors.toList());
//	}
//
//	@PostMapping
//	public WishListDto addToWishList(WishListDto wishListDto) {
//		Product product =  productService.getProduct(wishListDto.getProductId());
//		User user = userService.getUser(wishListDto.getUserId());
//
//		WishList wishList = new WishList(wishListDto.getAmount(), wishListDto.getSelectedSize());
//		wishList.setProduct(product);
//		wishList.setUser(user);
//		wishListService.addToWishList(wishList);
//
//		return wishListDto;
//
//	}
//
//	public void removeFromWishList(Long productID, String userID) {
//		wishListService.removeFromWishList(productID, userID);
//	}
}
