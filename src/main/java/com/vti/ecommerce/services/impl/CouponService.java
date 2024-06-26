package com.vti.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.Coupon;
import com.vti.ecommerce.repositories.jpa.ICouponRepository;

@Service
public class CouponService {
    @Autowired
    private ICouponRepository couponRepository;

    public List<Coupon> getAll() {
        return couponRepository.findAll();
    }

    public Coupon getByCode(String code) {
        return couponRepository.findByCode(code);
    }
}
