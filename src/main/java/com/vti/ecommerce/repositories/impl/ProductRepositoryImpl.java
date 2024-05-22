package com.vti.ecommerce.repositories.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.repositories.ProductRepository;
import com.vti.ecommerce.services.dto.ProductDto;
import com.vti.ecommerce.utils.EnManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private EntityManager entityManager;

    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Product findById(Long productID) {
		 Product p = entityManager.find(Product.class, productID);
        return p;

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

    @Override
    public ProductDto findById(String productID) {
        TypedQuery<ProductDto> query = entityManager.createQuery(
            "SELECT new com.vti.ecommerce.dto.ProductDTO(" +
            "p.productID, p.productName, p.description, p.isInStock, " +
            "p.rating, p.productDate, p.brandName, p.imageURL) " +
            "FROM Product p WHERE p.productID = :id", ProductDto.class);
        query.setParameter("id", productID);
        return query.getSingleResult();
    }
    

}
