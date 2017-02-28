package com.ncproject.webstore.dao.POJO;

import com.ncproject.webstore.entity.Customer;

import java.sql.Array;
import java.sql.Timestamp;

/**
 * Created by book on 01.01.2017.
 */
public class Orders {
    private int id;
    private int customer_id;
    private Timestamp data;
    private String status;
    private Array product_list;
    private double total;

    public Orders() {
    }
    public Orders(int id, int customer_id, Timestamp data, String status, Array product_list, double total) {
        this.id = id;
        this.customer_id = customer_id;
        this.data = data;
        this.status = status;
        this.product_list = product_list;
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

    public Array getProduct_list() {
        return product_list;
    }

    public void setProduct_list(Array product_list) {
        this.product_list = product_list;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
