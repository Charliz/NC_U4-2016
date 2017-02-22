package com.ncproject.webstore.ejb.beans;

import com.ncproject.webstore.dao.CartDAO;
import com.ncproject.webstore.dao.POJO.Cart;
import com.ncproject.webstore.dao.postgreSql.PostgreCartDAO;
import com.ncproject.webstore.ejb.CartBeanInterface;
import com.ncproject.webstore.entity.Customer;
import com.ncproject.webstore.entity.Product;

import javax.ejb.Stateful;
import java.util.ArrayList;

/**
 * Created by Черный on 21.02.2017.
 */
@Stateful
public class CartBean implements CartBeanInterface {
    @Override
    public ArrayList<Cart> getCart(Customer customer) {
        CartDAO cartDAO = new PostgreCartDAO();
        ArrayList<Cart> cart = null;
        try {
            cartDAO.readById(customer.getId());
        } catch (Exception e) {
            System.out.println("Get cart Bean exception");
            e.printStackTrace();
        }
        return cart;
    }

    public void AddToCart(Customer customer, Product product){
        CartDAO cartDAO = new PostgreCartDAO();
        try{
            cartDAO.addToCart(customer.getId(), product.getProd_id());
        }catch (Exception e){
            System.out.println("Delete from cart exception");
        }
    }

    public String getCartSumById(Customer customer){
        String sum = null;
        CartDAO cartDAO = new PostgreCartDAO();
        try{
            cartDAO.getCartSumById(customer.getId());
        }catch (Exception e){
            System.out.println("Get cartSum Bean exception");
            e.printStackTrace();
        }
        return sum;
    }

    public void DelFromCart(Cart cart){
        CartDAO cartDAO = new PostgreCartDAO();
        try{
            cartDAO.delFromCart(cart.getId());
        }catch (Exception e){
            System.out.println("Delete from cart exception");
        }
    }
}
