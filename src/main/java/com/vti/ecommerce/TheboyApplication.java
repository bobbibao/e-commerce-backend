package com.vti.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.vti.ecommerce.repositories.jpa")
@EnableElasticsearchRepositories(basePackages = "com.vti.ecommerce.repositories.elasticsearch")
public class TheboyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheboyApplication.class, args);
	}
}