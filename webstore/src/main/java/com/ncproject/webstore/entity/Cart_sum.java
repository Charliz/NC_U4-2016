package com.ncproject.webstore.entity;




public class Cart_sum {

    private int id;
    private String summary;
    private int cart_id;

    public Cart_sum(int id, String summary, int cart_id) {
        this.id = id;
        this.summary = summary;
        this.cart_id = cart_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }
}
