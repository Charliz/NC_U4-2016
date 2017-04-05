package com.ncproject.webstore.ejb;

import com.ncproject.webstore.entity.Customer;

import javax.ejb.Remote;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Черный on 01.03.2017.
 */
@Remote
public interface CustomerBeanInterface {

    void create(Customer customer);

    List<Customer> getAll();

    void delete(String email);

    Customer read(String login);

    void update(Customer customer);

    Customer readById(int id);

    boolean isAdmin(Customer customer) throws SQLException;
}
