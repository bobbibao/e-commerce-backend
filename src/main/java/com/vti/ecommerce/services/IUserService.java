package com.vti.ecommerce.services;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.ecommerce.domains.entities.Order;
import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.services.dto.OrderDto;

public interface IUserService extends UserDetailsService, IBaseService<User, Long>{
//    User getUser(String id);
//    User createUser(User User);
//    User updateUser(Long id, User User);
//    boolean deleteUser(Long id);
    User getUserByEmail(String email);
    List<OrderDto> getOrdersByEmail(String email);
}
