package com.vti.ecommerce.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EnManagerFactory {
	private final static String PERSISTENCE_UNIT_NAME = "jpa-hibernate-mysql";
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public EnManagerFactory() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void closeEnManager() {
		this.entityManager.close();
	}
	
	public void closeEnManagerFactory() {
		this.entityManagerFactory.close();
	}
}
