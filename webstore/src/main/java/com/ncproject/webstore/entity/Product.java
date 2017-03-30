package com.ncproject.webstore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

    private int prod_id;
    private String description;
    private String productName;
    private BigDecimal price;
    private String brand;
    private int quantity;

    public Product(int id, String description, String productName, BigDecimal price, String brand) {
        this.prod_id = id;
        this.description = description;
        this.productName = productName;
        this.price = price;
        this.brand = brand;

    }

    public Product(String description, String productName, BigDecimal price, String brand) {
        this.description = description;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
    }

    public Product() {

    }


    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prod_id=" + prod_id +
                ", description='" + description + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
