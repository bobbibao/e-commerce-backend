package com.vti.ecommerce.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.repositories.UserRepository;
import com.vti.ecommerce.services.UserService;
import com.vti.ecommerce.services.dto.CustomUserDetails;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public User getUser(String id) {
		return userRepository.findById(id);
	}

	@Override
	public User createUser(User User) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(Long id, User User) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username: " + username);
		User user = userRepository.findByUserName(username);
		System.out.println("user: " + user);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}
}
