package com.vti.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.ecommerce.domains.entities.Supplier;

public interface ISupplierRepository extends JpaRepository<Supplier, Integer> {
    // Supplier findById(Long supplierID);
    // SupplierDto findById(String supplierID);
    // List<Supplier> findAll();
    // boolean save(Supplier supplier);
    // boolean deleteById(Long supplierID);
    // List<Supplier> findBySupplierNameContaining(String search);
    // List<Map<String, Object>> countSupplierByCategory();

}
