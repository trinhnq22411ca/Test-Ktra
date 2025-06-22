package com.example.models;

import java.io.Serializable;

public class Product implements Serializable {
    private int ProductID;
    private String ProductName;
    private double UnitPrice;
    private String ImageLink;
    private int CategoryID;

    public Product() {
    }

    public Product(int productID, String productName, double unitPrice, String imageLink, int categoryID) {
        ProductID = productID;
        ProductName = productName;
        UnitPrice = unitPrice;
        ImageLink = imageLink;
        CategoryID = categoryID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }
}
