package com.ncproject.webstore.ejb.beans;

import com.ncproject.webstore.dao.OrdersDAO;
import com.ncproject.webstore.entity.Orders;
import com.ncproject.webstore.dao.postgreSql.PostgreOrdersDAO;
import com.ncproject.webstore.ejb.OrderBeanInterface;
import com.ncproject.webstore.entity.Customer;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.sql.DataSource;
import java.util.List;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

/**
 * Created by admin on 23.02.2017.
 */
@TransactionAttribute(REQUIRES_NEW)
@Stateful
@Remote(OrderBeanInterface.class)
public class OrderBean implements OrderBeanInterface {


    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;

    @Override
    public List<Orders> readById(Customer customer) {
        OrdersDAO ordersDAO = new PostgreOrdersDAO(dataSource);
        try {
            return ordersDAO.readById(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createOrder(Customer customer) {
        OrdersDAO ordersDAO = new PostgreOrdersDAO(dataSource);
        try {
            ordersDAO.createOrder(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Orders> getAllOrders() {
        OrdersDAO ordersDAO = new PostgreOrdersDAO(dataSource);
        try {
            return ordersDAO.getAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
