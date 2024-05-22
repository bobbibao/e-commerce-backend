package com.vti.ecommerce.utils;

import com.vti.ecommerce.domains.entities.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPA {
	public static void main(String[] args) {
		 EnManagerFactory em = new EnManagerFactory();
		 EntityManager entityManager = em.getEntityManager();
		 Product p = entityManager.find(Product.class, 205082351);
		 System.out.println(p);
	}
}
