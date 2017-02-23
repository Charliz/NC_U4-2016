package com.ncproject.webstore.ejb;

import com.ncproject.webstore.dao.POJO.Orders;
import com.ncproject.webstore.entity.Customer;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by admin on 23.02.2017.
 */
@Local
public interface OrderBeanInterface {
    List<Orders> readById(Customer customer);
    void createOrder(Customer customer);
}
