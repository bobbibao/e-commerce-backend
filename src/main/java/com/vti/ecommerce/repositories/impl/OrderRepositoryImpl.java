package com.vti.ecommerce.repositories.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.Order;
import com.vti.ecommerce.repositories.OrderRepository;

import jakarta.persistence.EntityManager;

@Repository
public class OrderRepositoryImpl implements OrderRepository{

    private EntityManager entityManager;

    public OrderRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public Order findById(Long orderID) {
        return entityManager.find(Order.class, orderID);
    }

    @Override
    public List<Order> findByUserId(String userID) {
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.userID = :userID", Order.class)
            .setParameter("userID", userID)
            .getResultList();
    }

    @Override
    public boolean save(Order order) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(order);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public boolean deleteById(Long orderID) {
        Order order = findById(orderID);
        if (order == null) {
            return false;
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(order);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
