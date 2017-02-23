package com.ncproject.webstore.ejb;

import com.ncproject.webstore.dao.POJO.Cart;
import com.ncproject.webstore.entity.Customer;

import javax.ejb.Local;
import java.util.ArrayList;

/**
 * Created by Черный on 21.02.2017.
 */
@Local
public interface CartBeanInterface {
    public ArrayList<Cart> getCart(Customer customer);

    void addToCart(Customer customer, String product_id);

    String getCartSumById(Customer customer);

    void delFromCart(int di);
}