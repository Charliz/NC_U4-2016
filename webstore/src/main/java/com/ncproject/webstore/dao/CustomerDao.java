package com.ncproject.webstore.dao;

import com.ncproject.webstore.entity.Customer;

public interface CustomerDao {

	void create(Customer customer);

	Customer read(String login);

	Customer readById(int id);

	void update(Customer customer);

}
