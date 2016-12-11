package com.ncproject.webstore.dao;

import com.ncproject.webstore.dao.PostgreSql.PostgreSqlCustomerDao;
import com.ncproject.webstore.entity.Customer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

	@Resource(lookup = "java:/PostgresNC")
	private DataSource dataSource;

	private static DaoFactory daoFactory;
	/*private String user = "postgres";
	private String password = "0130";
	private String url = "jdbc:postgresql://localhost:5432/onlinestore";*/

	public static void main(String[] args) {
		daoFactory = getInstance();
		CustomerDao customerDao = daoFactory.getCustomerDao();
		//Customer customer = customerDao.create("Login", "Password");
		Customer customer = customerDao.read("Login");
		//customerDao.update("Login", "Password3");
	}

	public static DaoFactory getInstance() {
		if (null == daoFactory) {
			daoFactory = new DaoFactory();
		}
		return daoFactory;
	}

	public Connection getConnection() {
		try {
			//return DriverManager.getConnection(url, user, password);
			return  dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("Connection exception.");
			return null;
		}
	}

	public CustomerDao getCustomerDao() {
		return (CustomerDao) new PostgreSqlCustomerDao();
	}
}
