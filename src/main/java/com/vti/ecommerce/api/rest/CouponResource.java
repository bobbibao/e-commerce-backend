package com.vti.ecommerce.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.services.impl.CouponService;

@RestController
@RequestMapping("/coupons")
@CrossOrigin(origins = "*")
public class CouponResource {

    @Autowired
    private CouponService service;

    @GetMapping
    public ResponseEntity getCoupons() {
        return ResponseEntity.ok(service.getAll());
    }
}
