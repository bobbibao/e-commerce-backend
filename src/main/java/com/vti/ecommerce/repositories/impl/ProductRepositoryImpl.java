package com.vti.ecommerce.repositories.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.Product;
import com.vti.ecommerce.repositories.ProductRepository;

import jakarta.persistence.EntityManager;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private EntityManager entityManager;

    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Product findById(Long productID) {
        return entityManager.find(Product.class, productID);

        // return entityManager.find(Product.class, productID) != null ? Optional.of(entityManager.find(Product.class, productID)) : Optional.empty();
        // return entityManager.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
        //     .setParameter("id", productID)
        //     .getResultList()
        //     .stream()
        //     .findFirst();
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
            .getResultList();
    }

    @Override
    public boolean save(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public boolean deleteById(Long productID) {
        Product product = findById(productID);
        if (product == null) {
            return false;
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(product);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
