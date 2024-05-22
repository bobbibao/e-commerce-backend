package com.vti.ecommerce.repositories.impl;

import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.repositories.UserRepository;

import jakarta.persistence.EntityManager;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findById(String userID) {
       return entityManager.find(User.class, userID);
    }

    @Override
    public User findByUserName(String userName) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class)
            .setParameter("userName", userName)
            .getSingleResult();
    }

    @Override
    public boolean save(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public boolean deleteById(String userID) {
        User user = findById(userID);
        if (user == null) {
            return false;
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
