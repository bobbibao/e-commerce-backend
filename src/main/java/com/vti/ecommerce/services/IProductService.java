package com.vti.ecommerce.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.services.dto.ProductDto;

public interface IProductService extends IBaseService<ProductDto, Long>{
   Product getProduct(Long id);
//    ProductDto createProduct(ProductDto productDto);
//    ProductDto updateProduct(Long id, ProductDto productDto);
//    boolean deleteProduct(Long id);
    List<ProductDto> getProductByPage(int page, int limit);
    List<ProductDto> searchProduct(String search, int page, int limit);
    String uploadFile(MultipartFile file) throws IOException;
}
