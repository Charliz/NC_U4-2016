package com.ncproject.webstore.ejb;

import com.ncproject.webstore.entity.Customer;

import javax.ejb.Local;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Черный on 01.03.2017.
 */
@Local
public interface CustomerBeanInterface {
    void create(Customer customer, DataSource dataSource);

    List<Customer> getAll(DataSource dataSource);

    void delete(String email, DataSource dataSource);

    Customer read(String login, DataSource dataSource);

    void update(Customer customer, DataSource dataSource);
}
