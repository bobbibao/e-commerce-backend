package com.vti.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.Order;
import com.vti.ecommerce.domains.entities.OrderDetail;
import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.repositories.IOrderRepository;
import com.vti.ecommerce.repositories.IUserRepository;
import com.vti.ecommerce.services.IUserService;
import com.vti.ecommerce.services.dto.CustomUserDetails;
import com.vti.ecommerce.services.dto.OrderDetailDto;
import com.vti.ecommerce.services.dto.OrderDto;
import com.vti.ecommerce.services.dto.ProductDto;

@Service
public class UserServiceImpl implements IUserService{

	
	private final IUserRepository userRepository;
	private final IOrderRepository orderRepository;

	public UserServiceImpl(IUserRepository userRepository, IOrderRepository orderRepository) {
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
	}
//	@Override
//	public User getUser(String id) {
//		return userRepository.findById(id);
//	}
//
//	@Override
//	public User createUser(User User) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User updateUser(Long id, User User) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean deleteUser(Long id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("email: " + username);
		User user = userRepository.findByEmail(username);
		System.out.println("user: " + user);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}
	@Override
	public boolean insert(User s) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(User s) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteById(Long p) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Optional<User> getById(Long p) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updatePartial(Long p, Map<String, Object> updates) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteMany(List<Long> userIds) {
		// TODO Auto-generated method stub
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDto> getOrdersByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return new ArrayList<>();
		}
		List<Order> orders = orderRepository.findByUser(user);
		List<OrderDto> orderDtos = orders.stream().map(order -> convertOrderDto(order)).collect(Collectors.toList());
		return orderDtos;
	}
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimple  mented method 'getUserByEmail'");
	}

	public OrderDto convertOrderDto(Order order) {
		Set<OrderDetailDto> orderDetailDtos = order.getOrderDetails().stream()
			.map(orderDetail -> convertOrderDetailDto(orderDetail))
			.collect(Collectors.toSet());
		return new OrderDto(order.getOrderID(), order.getUser().getUserID(), orderDetailDtos, order.getOrderTotal());
	}

	public OrderDetailDto convertOrderDetailDto(OrderDetail orderDetail) {
		System.out.println("orderDetail: " + orderDetail);
		return new OrderDetailDto(orderDetail.getOrderDetailID(), convertProductDto(orderDetail.getProduct()), orderDetail.getAmount(), orderDetail.getSelectedSize());
	}

	public ProductDto convertProductDto(Product product) {
		System.out.println("product: " + product.getProductName());
		return new ProductDto(product.getProductID(), product.getProductName(), product.getDescription(), product.isInStock(), product.getRating(), product.getBrandName(), product.getImageURL(), product.getPrices().get(0).getPriceValue());
	}
}

