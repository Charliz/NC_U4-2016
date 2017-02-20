package com.ncproject.webstore.dao;


import com.ncproject.webstore.dao.postgreSql.PostgreCatalogDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DaoFactory2 {

	private static DaoFactory2 daoFactory;
	private static String type;
	private String user;
	private String password;
	private String url;
	private String driver;


	private DaoFactory2() throws Exception {
		try {
			loadProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
//			log.error("Driver not found\n", e);
		}
	}

	public static DaoFactory2 getInstance(){
		if (null == daoFactory) {
			try {
				daoFactory = new DaoFactory2();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return daoFactory;
	}

	public Connection getConnection() throws Exception {
//		log.trace("Driver manager get connection");
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new Exception("No connection to DB", e);
		}
	}

	public CatalogDAO getGroupDao() {
		if (type.equalsIgnoreCase("postgres")) {
			return new PostgreCatalogDAO();
		} else {
			return new PostgreCatalogDAO();
		}
	}

    private void loadProperties() throws Exception {

        type = "postgres";
        user = "postgres";
        password = "1234";
        url = "jdbc:postgresql://localhost:5432/onlinestore";
        driver = "org.postgresql.Driver";
	}
}
