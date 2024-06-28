package com.vti.ecommerce.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import com.vti.ecommerce.domains.entities.Product;

@Configuration
public class ElasticsearchConfig implements ApplicationListener<ContextRefreshedEvent> {

    private final ElasticsearchOperations elasticsearchOperations;

    public ElasticsearchConfig(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createProductIndex();
    }

    private void createProductIndex() {
        IndexCoordinates indexCoordinates = IndexCoordinates.of("product");
        if (!elasticsearchOperations.indexOps(indexCoordinates).exists()) {
            elasticsearchOperations.indexOps(Product.class).create();
            // Optionally, you can also define mappings and settings for your index here
        }
    }
}