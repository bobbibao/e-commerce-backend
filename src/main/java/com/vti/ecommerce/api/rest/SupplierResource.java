package com.vti.ecommerce.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.services.ISupplierService;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin(origins = "*")
public class SupplierResource {
    @Autowired
    private ISupplierService supplierService;

    @GetMapping
    public ResponseEntity<?> getAllSupplier() {
        return ResponseEntity.ok(supplierService.getAll());
    }

}
