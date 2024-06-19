package com.vti.ecommerce.services;

import java.util.List;

import com.vti.ecommerce.domains.entities.Supplier;

public interface ISupplierService {
    List<Supplier> getAll();
}
