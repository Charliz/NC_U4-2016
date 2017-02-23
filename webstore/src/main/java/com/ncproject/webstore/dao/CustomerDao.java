package com.ncproject.webstore.dao;

import com.ncproject.webstore.entity.Customer;

public interface CustomerDao {

	void create(Customer customer);

	Customer read(String login);

	void update(Customer customer);

	void setRole(Customer customer, String role);

}
