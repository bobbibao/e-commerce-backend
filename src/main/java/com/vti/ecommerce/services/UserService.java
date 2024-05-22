package com.vti.ecommerce.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.ecommerce.domains.entities.User;

public interface UserService extends UserDetailsService{
    User getUser(String id);
    User createUser(User User);
    User updateUser(Long id, User User);
    boolean deleteUser(Long id);
}
