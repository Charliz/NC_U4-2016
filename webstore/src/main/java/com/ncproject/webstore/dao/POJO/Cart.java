package com.ncproject.webstore.dao.POJO;


import java.util.ArrayList;

public class Cart {

    private int id;
    private int customer_id;
    private ArrayList<Integer> products_id;
    private ArrayList<Integer> counts;

    public Cart(int id, int customer_id, ArrayList<Integer> products_id, ArrayList<Integer> counts) {
        this.id = id;
        this.customer_id = customer_id;
        this.products_id = products_id;
        this.counts = counts;
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

    public ArrayList<Integer> getProduct_id() {
        return products_id;
    }

    public void setProduct_id(ArrayList<Integer> products_id) {
        this.products_id = products_id;
    }

    public ArrayList<Integer> getCount() {
        return counts;
    }

    public void setCount(ArrayList<Integer> counts) {
        this.counts = counts;
    }
}
