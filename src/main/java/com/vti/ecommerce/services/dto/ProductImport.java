package com.vti.ecommerce.services.dto;

public class ProductImport {
    private Long productId;
    private int quantity;

    public ProductImport() {
    }

    public ProductImport(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductImport{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
    
}
