package com.vti.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.Coupon;

@Repository
public interface ICouponRepository extends JpaRepository<Coupon, Long>{
    Coupon findByCode(String code);
}
