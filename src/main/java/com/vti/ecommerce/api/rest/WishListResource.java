package com.vti.ecommerce.api.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.domains.entities.WishList;
import com.vti.ecommerce.repositories.jpa.IUserRepository;
import com.vti.ecommerce.services.IProductService;
import com.vti.ecommerce.services.IUserService;
import com.vti.ecommerce.services.IWishListService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/wishlist")
public class WishListResource {

	private final IWishListService wishListService;
	private final IProductService productService;
	private final IUserService userService;
	private final IUserRepository userRepository;
	
	public WishListResource(IWishListService wishListService, IProductService productService, IUserService userService, IUserRepository userRepository) {
		this.wishListService = wishListService;
		this.productService = productService;
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@GetMapping("/{userID}")
	public ResponseEntity getWishList(@PathVariable String userID) {
		return ResponseEntity.ok(wishListService.getWishListByUserId(userID));
	}

	@PostMapping("/{userID}")
	public ResponseEntity addToWishList(@PathVariable String userID, @RequestBody Map<String, String> wishListMap) {
		Product product = productService.getProduct(Long.valueOf(wishListMap.get("productID")));
		User user = userRepository.findById(userID).get();

		WishList wishList = new WishList(Integer.parseInt(wishListMap.get("amount")), wishListMap.get("selectedSize"));
		wishList.setProduct(product);
		wishList.setUser(user);
		
		boolean result = wishListService.addToWishList(wishList);
		if (result) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(400).build();
	}

	@DeleteMapping("/{wishListID}")
	public ResponseEntity removeFromWishList(@PathVariable Long wishListID) {
		wishListService.removeFromWishList(wishListID);
		return ResponseEntity.ok().build();
	}
}