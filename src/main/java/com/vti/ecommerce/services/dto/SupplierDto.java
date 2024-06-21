package com.vti.ecommerce.services.dto;

public class SupplierDto {
    private int supplierID;
    private String supplierName;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private boolean supplierStatus;
    private String supplierDescription;
    private String supplierLogo;
    private String supplierWebsite;
    private String address;
    
    public SupplierDto() {
    }

    public SupplierDto(int supplierID, String supplierName, String contactName, String contactPhone, String contactEmail,
            boolean supplierStatus, String supplierDescription, String supplierLogo, String supplierWebsite,
            String address) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.supplierStatus = supplierStatus;
        this.supplierDescription = supplierDescription;
        this.supplierLogo = supplierLogo;
        this.supplierWebsite = supplierWebsite;
        this.address = address;
    }

}