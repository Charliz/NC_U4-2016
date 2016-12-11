package com.ncproject.webstore.dao;

import com.ncproject.webstore.entity.Customer;

public interface CustomerDao {

	Customer create(String login, String password);

	Customer read(String login);

	void update(String login, String password);

}
