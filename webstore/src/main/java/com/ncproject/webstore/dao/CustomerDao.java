package com.ncproject.webstore.dao;

import com.ncproject.webstore.entity.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface CustomerDao {

	RowMapper<Customer> ROW_MAPPER_CUST = (ResultSet resultSet, int rowNum) -> {
		Customer customer = new Customer(resultSet.getString("login"),
				resultSet.getString("password"), resultSet.getString("email"),
				resultSet.getString("name"), resultSet.getString("address"),
				resultSet.getString("payment"));
		customer.setId(resultSet.getInt("id"));
		return customer;
	};

	void create(Customer customer);

	List<Customer> getAll();

	void delete(String email);

	Customer read(String login);

	void update(Customer customer);

}
