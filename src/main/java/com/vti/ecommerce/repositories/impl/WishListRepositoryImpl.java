package com.vti.ecommerce.repositories.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.WishList;
import com.vti.ecommerce.repositories.WishListRepository;

import jakarta.persistence.EntityManager;

@Repository
public class WishListRepositoryImpl implements WishListRepository{

    private EntityManager entityManager;

    public WishListRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public WishList findById(Long productID, String userID) {
        // return entityManager.find(WishList.class, new WishList.WishListId(productID, userID));
        return entityManager.createQuery("SELECT w FROM WishList w WHERE w.product.id = :productID AND w.userID = :userID", WishList.class)
            .setParameter("productID", productID)
            .setParameter("userID", userID)
            .getSingleResult();
    }

    @Override
    public List<WishList> findByUserId(String userID) {
        return entityManager.createQuery("SELECT w FROM WishList w WHERE w.userID = :userID", WishList.class)
            .setParameter("userID", userID)
            .getResultList();
    }

    @Override
    public boolean save(WishList wishList) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(wishList);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public boolean deleteById(Long productID, String userID) {
        WishList wishList = findById(productID, userID);
        if (wishList == null) {
            return false;
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(wishList);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
