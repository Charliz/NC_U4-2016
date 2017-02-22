package com.ncproject.webstore.ejb;

import com.ncproject.webstore.dao.POJO.Cart;
import com.ncproject.webstore.entity.Customer;
import com.ncproject.webstore.entity.Product;

import javax.ejb.Remote;
import java.util.ArrayList;

/**
 * Created by Черный on 21.02.2017.
 */
@Remote
public interface CartBeanInterface {
    public ArrayList<Cart> getCart(Customer customer);

    void AddToCart(Customer customer, Product product);

    String getCartSumById(Customer customer);

    void DelFromCart(Cart cart);
}