package com.vti.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.RestockHistory;
import com.vti.ecommerce.repositories.jpa.IRestockHistoryRepository;
import com.vti.ecommerce.services.IRestockHistoryService;

@Service
public class RestockHistoryServiceImpl implements IRestockHistoryService{
	
	@Autowired
	private final IRestockHistoryRepository cartRepository;

	public RestockHistoryServiceImpl(IRestockHistoryRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

//	@Override
//	public RestockHistory getRestockHistory(Long restockHistoryID) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<RestockHistory> getRestockHistoryByProductId(String productID) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
