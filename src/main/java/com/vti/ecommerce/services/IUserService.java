package com.vti.ecommerce.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.services.dto.OrderDto;
import com.vti.ecommerce.services.dto.UserDto;

public interface IUserService extends UserDetailsService, IBaseService<UserDto, String>{
//    User getUser(String id);
//    User createUser(User User);
//    User updateUser(Long id, User User);
//    boolean deleteUser(Long id);
    User getUserByEmail(String email);
    List<OrderDto> getOrdersByEmail(String email);
    String getUserID(String email);
    UserDto updateUserRole(String id, String role);
    boolean changePassword(String email, String oldPassword, String newPassword);
    
}
