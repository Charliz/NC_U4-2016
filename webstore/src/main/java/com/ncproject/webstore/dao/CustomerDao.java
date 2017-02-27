package com.ncproject.webstore.dao;

import com.ncproject.webstore.entity.Customer;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public interface CustomerDao {

	RowMapper<Customer> ROW_MAPPER_CUST = (ResultSet resultSet, int rowNum) -> {
		return new Customer(resultSet.getString("login"),
				resultSet.getString("password"), resultSet.getString("email"),
				resultSet.getString("name"), resultSet.getString("address"),
				resultSet.getString("payment"));
	};

	void create(Customer customer);

	List<Customer> getAll();

	void delete(String email);
	void setDataSource();

	Customer read(String login);

	void update(Customer customer);

}
