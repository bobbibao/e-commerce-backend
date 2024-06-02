package com.vti.ecommerce.api.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.config.jwt.TokenProvider;
import com.vti.ecommerce.services.IRestockHistoryService;
import com.vti.ecommerce.services.IProductService;
import com.vti.ecommerce.services.IUserService;

@RestController
@RequestMapping("/api/carts")
public class RestockHistoryResource {

	private final IRestockHistoryService cartService;
	private final IProductService productService;
	private final IUserService userService;

	@Autowired
    private TokenProvider tokenProvider;

	public RestockHistoryResource(IRestockHistoryService cartService, IProductService productService, IUserService userService) {
		this.cartService = cartService;
		this.productService = productService;
		this.userService = userService;
	}

	
	
}