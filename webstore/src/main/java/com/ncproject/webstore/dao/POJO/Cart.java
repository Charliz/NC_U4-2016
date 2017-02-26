package com.ncproject.webstore.dao.POJO;


import java.util.ArrayList;

public class Cart {
    private int id;
    private int customer_id;
    private int product_id;
    private int count;

    public Cart(int id, int customer_id, int product_id, int count) {
        this.id = id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
