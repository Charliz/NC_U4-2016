package com.ncproject.webstore.ejb;

import com.ncproject.webstore.entity.Cart;
import com.ncproject.webstore.entity.Customer;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.sql.DataSource;
import java.util.ArrayList;

/**
 * Created by Черный on 21.02.2017.
 */
@Remote
public interface CartBeanInterface {

    public ArrayList<Cart> getCart(Customer customer);

    void addToCart(Customer customer, String product_id);

    String getCartSumById(Customer customer);

    void delFromCart(int id);
}