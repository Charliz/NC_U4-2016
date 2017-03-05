package com.ncproject.webstore.dao;

import com.ncproject.webstore.entity.Cart;

import java.util.List;


public interface CartDAO {
    List<Cart> readById(int id);
    void addToCart(int customer_id, int product_id);
    String getCartSumById(int id);
    void delFromCart(int id);
}
