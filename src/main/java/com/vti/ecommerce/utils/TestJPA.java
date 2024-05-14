package com.vti.ecommerce.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPA {
	public static void main(String[] args) {
		 EntityManagerFactory em = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
		
	}
}
