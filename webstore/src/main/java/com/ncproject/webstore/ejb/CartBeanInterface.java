package com.ncproject.webstore.ejb;

import com.ncproject.webstore.entity.Cart;
import com.ncproject.webstore.entity.Customer;

import javax.ejb.Local;
import javax.sql.DataSource;
import java.util.ArrayList;

/**
 * Created by Черный on 21.02.2017.
 */
@Local
public interface CartBeanInterface {
    public ArrayList<Cart> getCart(Customer customer, DataSource dataSource);

    void addToCart(Customer customer, String product_id, DataSource dataSource);

    String getCartSumById(Customer customer, DataSource dataSource);

    void delFromCart(int id, DataSource dataSource);
}