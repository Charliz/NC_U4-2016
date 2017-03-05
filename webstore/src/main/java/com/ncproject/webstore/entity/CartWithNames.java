package com.ncproject.webstore.entity;

/**
 * Created by book on 30.12.2016.
 */
public class CartWithNames {

    private int id;
    private String name;
    private int count;
    private double summary;

    public CartWithNames(int id, String name, int count, double summary) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSummary() {
        return summary;
    }

    public void setSummary(double summary) {
        this.summary = summary;
    }
}
