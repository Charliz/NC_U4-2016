package com.ncproject.webstore.ejb.beans;

import com.ncproject.webstore.dao.CartDAO;
import com.ncproject.webstore.entity.Cart;
import com.ncproject.webstore.dao.postgreSql.PostgreCartDAO;
import com.ncproject.webstore.ejb.CartBeanInterface;
import com.ncproject.webstore.entity.Customer;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

/**
 * Created by Черный on 21.02.2017.
 */
@TransactionAttribute(REQUIRES_NEW)
@Stateful
@Remote(CartBeanInterface.class)
public class CartBean implements CartBeanInterface {

    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;

    @Override
    public List<Cart> getCart(Customer customer) {
        CartDAO cartDAO = new PostgreCartDAO(dataSource);
        List<Cart> cart = null;
        try {
            cart = cartDAO.readById(customer.getId());
        } catch (Exception e) {
            System.out.println("Get cart Bean exception");
            e.printStackTrace();
        }
        return cart;
    }

    public void addToCart(Customer customer, String product_id){
        CartDAO cartDAO = new PostgreCartDAO(dataSource);
        try{
            cartDAO.addToCart(customer.getId(), Integer.parseInt(product_id));
        }catch (Exception e){
            System.out.println("Delete from cart exception");
        }
    }

    public String getCartSumById(Customer customer){
        CartDAO cartDAO = new PostgreCartDAO(dataSource);
        String sum = cartDAO.getCartSumById(customer.getId());
        return sum;
    }

    public void delFromCart(int id){
        CartDAO cartDAO = new PostgreCartDAO(dataSource);
        cartDAO.delFromCart(id);
    }
}
