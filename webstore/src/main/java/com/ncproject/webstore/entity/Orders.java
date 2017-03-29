package com.ncproject.webstore.entity;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Timestamp;

/**
 * Created by book on 01.01.2017.
 */
public class Orders implements Serializable{
    private int id;
    private int customer_id;
    private Timestamp data;
    private String status;
    private String productlist;
    private double total;

    public Orders() {
    }
    public Orders(int id, int customer_id, Timestamp data, String status, String productlist, double total) {
        this.id = id;
        this.customer_id = customer_id;
        this.data = data;
        this.status = status;
        this.productlist = productlist;
        this.total = total;
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

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductlist() {
        return productlist;
    }

    public void setProductlist(String productlist) {
        this.productlist = productlist;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
