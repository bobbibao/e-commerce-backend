package com.vti.ecommerce.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.Role;
import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.domains.entities.UserRole;
import com.vti.ecommerce.repositories.IOrderRepository;
import com.vti.ecommerce.repositories.IUserRepository;
import com.vti.ecommerce.services.IUserService;
import com.vti.ecommerce.services.dto.CustomUserDetails;
import com.vti.ecommerce.services.dto.OrderDto;
import com.vti.ecommerce.services.dto.UserDto;
import com.vti.ecommerce.services.dto.WishListDto;

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
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}
	@Override
	public boolean updatePartial(String p, Map<String, Object> updates) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteMany(List<String> userIds) {
		// TODO Auto-generated method stub
		return false;
	}
	// @SuppressWarnings("unchecked")
	// @Override
	// public List<OrderDto> getOrdersByEmail(String email) {
	// 	User user = userRepository.findByEmail(email);
	// 	if (user == null) {
	// 		return new ArrayList<>();
	// 	}
	// 	List<Order> orders = orderRepository.findByUser(user);
	// 	List<OrderDto> orderDtos = orders.stream().map(order -> convertOrderDto(order)).collect(Collectors.toList());
	// 	return orderDtos;
	// }
	// @Override
	// public User getUserByEmail(String email) {
	// 	// TODO Auto-generated method stub
	// 	throw new UnsupportedOperationException("Unimple  mented method 'getUserByEmail'");
	// }
	@Override
	public boolean insert(UserDto s) {
		return userRepository.save(s.convertToEntity()) != null;
	}
	@Override
	public boolean update(UserDto s) {
		return userRepository.save(s.convertToEntity()) != null;
	}
	@Override
	public boolean deleteById(String p) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
	}
	@Override
	public Optional<UserDto> getById(String p) {
		return userRepository.findById(p).map(user -> convertUserDto(user));
	}
	@Override
	public List<UserDto> getAll() {
		return userRepository.findAll().stream().map(user -> convertUserDto(user)).collect(Collectors.toList());
	}
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getUserByEmail'");
	}
	@Override
	public List<OrderDto> getOrdersByEmail(String email) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getOrdersByEmail'");
	}

	public UserDto convertUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getUserID());
		userDto.setName(user.getFirstName());
		userDto.setLastname(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setPhone(user.getPhone());
		userDto.setAdress(user.getAddress().getAddress());
		userDto.setPassword(user.getPasswordHash());
		try{
			userDto.setRole(user.getUserRoles().stream().map(userRole -> {
				return userRole.getRole().getRoleName();
			}).collect(Collectors.toList()).get(user.getUserRoles().size() - 1));
		} catch (Exception e) {
			userDto.setRole("User");
		}

		userDto.setUserWishlist(user.getUserWishLists().stream().map(wishList -> {
			return new WishListDto(wishList);
		}).collect(Collectors.toList()));

		return userDto;
	}
	// public OrderDto convertOrderDto(Order order) {
	// 	Set<OrderDetailDto> orderDetailDtos = order.getOrderDetails().stream()
	// 		.map(orderDetail -> convertOrderDetailDto(orderDetail))
	// 		.collect(Collectors.toSet());
	// 	return new OrderDto(order.getOrderID(), order.getUser().getUserID(), orderDetailDtos, order.getOrderTotal());
	// }
	@Override
	public String getUserID(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return null;
		}
		return user.getUserID();
	}

	// public OrderDetailDto convertOrderDetailDto(OrderDetail orderDetail) {
	// 	System.out.println("orderDetail: " + orderDetail);
	// 	return new OrderDetailDto(orderDetail.getOrderDetailID(), convertProductDto(orderDetail.getProduct()), orderDetail.getAmount(), orderDetail.getSelectedSize());
	// }

	// public ProductDto convertProductDto(Product product) {
	// 	System.out.println("product: " + product.getProductName());
	// 	return new ProductDto(product.getProductID(), product.getProductName(), product.getDescription(), product.isInStock(), product.getRating(), product.getBrandName(), product.getImageURL(), product.getPrices().get(0).getPriceValue());
	// }

    @Override
    public UserDto updateUserRole(String id, String role) {
		User user = userRepository.findById(id).get();
		if (user == null) {
			return null;
		}
		Role newRole = new Role();
		newRole.setRoleName(role);
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(newRole);
		user.getUserRoles().add(userRole);
		
		return convertUserDto(userRepository.save(user));
    }
}

