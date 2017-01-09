package com.ncproject.webstore.dao.POJO;

import org.json.simple.JSONObject;

/**
 * Created by One on 01.12.2016.
 */
public class storeCatalog {
    private int id;
    private String name;
    private String description;
    private String price;

    public storeCatalog(int id, String name, String description, String price) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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
