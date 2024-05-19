package com.vti.ecommerce.services;

import java.util.Optional;

import com.vti.ecommerce.domains.Product;
import com.vti.ecommerce.services.dto.ProductDto;

public interface ProductService {
    Product getProduct(Long id);
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Long id, ProductDto productDto);
    boolean deleteProduct(Long id);
}
