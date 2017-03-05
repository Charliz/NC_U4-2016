package com.ncproject.webstore.ejb.beans;

import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.dao.postgreSql.PostgreSqlCustomerDao;
import com.ncproject.webstore.ejb.CustomerBeanInterface;
import com.ncproject.webstore.entity.Customer;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import java.sql.ResultSet;
import java.util.List;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

/**
 * Created by Черный on 01.03.2017.
 */
@TransactionAttribute(REQUIRES_NEW)
@Stateful
public class CustomerBean implements CustomerBeanInterface {
    @Override
    public void create(Customer customer, DataSource dataSource) {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        customerDao.create(customer);
    }

    @Override
    public List<Customer> getAll(DataSource dataSource) {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        return customerDao.getAll();
    }

    @Override
    public void delete(String email, DataSource dataSource) {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        customerDao.delete(email);
    }

    @Override
    public Customer read(String login, DataSource dataSource) {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        return customerDao.read(login);
    }

    @Override
    public void update(Customer customer, DataSource dataSource) {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        customerDao.update(customer);
    }
}
