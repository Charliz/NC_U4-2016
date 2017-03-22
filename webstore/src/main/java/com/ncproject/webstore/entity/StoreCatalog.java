package com.ncproject.webstore.entity;

import org.json.simple.JSONObject;

import java.io.Serializable;

/**
 * Created by One on 01.12.2016.
 */
public class StoreCatalog implements Serializable {
    private int id;
    private String name;
    private String description;
    private String price;
    private int quantity;

    public StoreCatalog(int id, String name, String description, String price, int quantity) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + "_" + name + "_" + description + "_" + price;
    }

    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("id", id);
        jo.put("name", name);
        jo.put("description", description);
        jo.put("price", price);
        return jo;
    }

}
