package com.vti.ecommerce.services;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.services.dto.ProductDto;
import com.vti.ecommerce.services.dto.ProductForSave;
import com.vti.ecommerce.services.dto.ProductImport;

public interface IProductService extends IBaseService<ProductDto, Long>{
   Product getProduct(Long id);
   ProductForSave create(ProductForSave productForSave);
//    ProductDto createProduct(ProductDto productDto);
//    ProductDto updateProduct(Long id, ProductDto productDto);
//    boolean deleteProduct(Long id);
    List<ProductDto> getProductByPage(int page, int limit);
    List<ProductDto> searchProduct(String search, int page, int limit);
    String uploadFile(MultipartFile file) throws IOException;
    void importProducts(List<ProductImport> productImports, int supplierId);
    Page<ProductDto> searchProducts(String productName, int page, int size);
    Object countProductByCategory();
}
