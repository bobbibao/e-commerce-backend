package com.vti.ecommerce.services.dto;
import lombok.Data;

// name: '',
//     brand: '',
//     description: '',
//     category: '',
//     availableSizes: '',
//     price: '',
//     importPrice: '',
//     stock: '',
//     sku: '',
//     supplier: '',
//     mainImage: '',
//     additionalImages: [],
@Data
public class ProductForSave {
    private String name;
    private String brand;
    private String description;
    private String category;
    private String availableSizes;
    private double price;
    private double importPrice;
    private int stock;
    private String sku;
    private int  supplier;
    private String mainImage;
    private String[] additionalImages;

    public ProductForSave() {
    }

    public ProductForSave(String name, String brand, String description, String category, String availableSizes, double price, double importPrice, int stock, String sku, int supplier, String mainImage, String[] additionalImages) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.category = category;
        this.availableSizes = availableSizes;
        this.price = price;
        this.importPrice = importPrice;
        this.stock = stock;
        this.sku = sku;
        this.supplier = supplier;
        this.mainImage = mainImage;
        this.additionalImages = additionalImages;
    }
}
