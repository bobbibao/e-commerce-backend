package com.vti.ecommerce.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;

@Configuration
public class EntityManagerConfig {

    @Bean
    public EntityManager entityManager() {
        EnManagerFactory enManagerFactory = new EnManagerFactory();
        return enManagerFactory.getEntityManager();
    }
}