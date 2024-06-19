package com.vti.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.Supplier;
import com.vti.ecommerce.repositories.ISupplierRepository;
import com.vti.ecommerce.services.ISupplierService;

@Service
public class SupplierServiceImpl implements ISupplierService{

    @Autowired
    private ISupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

}
