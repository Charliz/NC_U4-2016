package com.ncproject.webstore.ejb;

import com.ncproject.webstore.entity.Orders;
import com.ncproject.webstore.entity.Customer;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by admin on 23.02.2017.
 */
@Remote
public interface OrderBeanInterface {
    List<Orders> readById(Customer customer);
    void createOrder(Customer customer);
    List<Orders> getAllOrders();
}
