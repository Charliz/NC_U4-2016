package com.ncproject.webstore.entity;

import java.math.BigDecimal;

public class Product {

    private int prod_id;
    private int category;
    private String description;
    private String productName;
    private BigDecimal price;
    private String brand;

    public Product(int id, int category, String description, String productName, BigDecimal price, String brand) {
        this.prod_id = id;
        this.category = category;
        this.description = description;
        this.productName = productName;
        this.price = price;
        this.brand = brand;

    }

    public Product(int category, String description, String productName, BigDecimal price, String brand) {
        this.category = category;
        this.description = description;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "com.ncproject.webstore.entity.Product{" +
                "prod_id=" + prod_id +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
