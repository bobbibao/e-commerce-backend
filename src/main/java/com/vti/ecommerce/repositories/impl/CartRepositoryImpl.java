package com.vti.ecommerce.repositories.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.Cart;
import com.vti.ecommerce.repositories.CartRepository;

import jakarta.persistence.EntityManager;

@Repository
public class CartRepositoryImpl implements CartRepository{

    private EntityManager entityManager;

    public CartRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public Cart findById(Long productID, String userID) {
        // return entityManager.find(Cart.class, new Cart.CartId(productID, userID));
        return entityManager.createQuery("SELECT c FROM Cart c WHERE c.product.id = :productID AND c.userID = :userID", Cart.class)
            .setParameter("productID", productID)
            .setParameter("userID", userID)
            .getSingleResult();
    }

    @Override
    public List<Cart> findByUserId(String userID) {
        return entityManager.createQuery("SELECT c FROM Cart c WHERE c.userID = :userID", Cart.class)
            .setParameter("userID", userID)
            .getResultList();
    }

    @Override
    public Cart save(Cart cart) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cart);
            entityManager.getTransaction().commit();
            return cart;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Cart deleteById(Long productID, String userID) {
        Cart cart = findById(productID, userID);
        if (cart == null) {
            return null;
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(cart);
            entityManager.getTransaction().commit();
            return cart;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
