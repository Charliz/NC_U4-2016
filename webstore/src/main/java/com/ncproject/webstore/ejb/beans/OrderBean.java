package com.ncproject.webstore.ejb.beans;

import com.ncproject.webstore.dao.OrdersDAO;
import com.ncproject.webstore.dao.POJO.Orders;
import com.ncproject.webstore.dao.postgreSql.PostgreOrdersDAO;
import com.ncproject.webstore.ejb.OrderBeanInterface;
import com.ncproject.webstore.entity.Customer;

import javax.ejb.Stateful;
import java.util.List;

/**
 * Created by admin on 23.02.2017.
 */
@Stateful
public class OrderBean implements OrderBeanInterface {
    OrdersDAO ordersDAO = new PostgreOrdersDAO();

    @Override
    public List<Orders> readById(Customer customer) {
        try {
            return ordersDAO.readById(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createOrder(Customer customer) {
        try {
            ordersDAO.createOrder(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
