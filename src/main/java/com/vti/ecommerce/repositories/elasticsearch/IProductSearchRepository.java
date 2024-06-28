package com.vti.ecommerce.repositories.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.vti.ecommerce.domains.entities.Product;

@Repository
public interface IProductSearchRepository extends ElasticsearchRepository<Product, Long> {
    Page<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);
}
