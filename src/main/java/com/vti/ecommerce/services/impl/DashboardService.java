package com.vti.ecommerce.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.repositories.jpa.IUserRepository;
import com.vti.ecommerce.services.IDashboardService;

@Service
public class DashboardService implements IDashboardService{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Object countGenders() {
        return userRepository.countUserByGender();
    }

    @Override
    public long countByGender(String gender) {
        return userRepository.countByGender(gender);
    }
}
