package com.ncproject.webstore.ejb.beans;

import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.dao.postgreSql.PostgreSqlCustomerDao;
import com.ncproject.webstore.ejb.CustomerBeanInterface;
import com.ncproject.webstore.entity.Customer;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.sql.DataSource;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

/**
 * Created by Черный on 01.03.2017.
 */
@TransactionAttribute(REQUIRES_NEW)
@Stateful
@Remote(CustomerBeanInterface.class)
public class CustomerBean implements CustomerBeanInterface {

    @Resource(lookup = "java:/PostgresXADS")
    private DataSource dataSource;

//    CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);

    @Override
    public void create(Customer customer) {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        customerDao.create(customer);
    }

    @Override
    public List<Customer> getAll() {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        return customerDao.getAll();
    }

    @Override
    public void delete(String email) {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        customerDao.delete(email);
    }

    @Override
    public Customer read(String login) {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        return customerDao.read(login);
    }

    @Override
    public void update(Customer customer) {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        customerDao.update(customer);
    }

    @Override
    public Customer readById(int id) {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        return customerDao.readById(id);
    }

    @Override
    public boolean isAdmin(Customer customer) throws SQLException {
        CustomerDao customerDao = new PostgreSqlCustomerDao(dataSource);
        return customerDao.isAdmin(customer);
    }
}
