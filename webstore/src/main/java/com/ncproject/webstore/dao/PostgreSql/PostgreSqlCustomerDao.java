package com.ncproject.webstore.dao.PostgreSql;

import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.dao.DaoFactory;
import com.ncproject.webstore.dao.JdbcUtils;
import com.ncproject.webstore.entity.Customer;

import java.sql.*;


public class PostgreSqlCustomerDao implements CustomerDao {
	private DaoFactory daoFactory = DaoFactory.getInstance();

	@Override
	public Customer create(String login, String password){
		String sql = "insert into registered_customers values (DEFAULT,?,?,123);";

		Customer customer = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			customer = parseResultSet(resultSet);
		} catch (SQLException e) {
		} finally {
			JdbcUtils.closeQuietly(resultSet);
			JdbcUtils.closeQuietly(preparedStatement);
			JdbcUtils.closeQuietly(connection);
		}
		return customer;
	}

	@Override
	public Customer read(String login){
		String sql = "select * from registered_customers where username = ?;";

		Customer customer = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer = parseResultSet(resultSet);
			}
		} catch (SQLException e) {
		} finally {
			JdbcUtils.closeQuietly(resultSet);
			JdbcUtils.closeQuietly(preparedStatement);
			JdbcUtils.closeQuietly(connection);
		}
		return customer;
	}

	@Override
	public void update(String login, String password){

		String sql = "update registered_customers set password = ? where username = ?";
		//Customer customer = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, login);
			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			//customer = parseResultSet(resultSet);
		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println("Query exception");
		} finally {
			JdbcUtils.closeQuietly(resultSet);
			JdbcUtils.closeQuietly(preparedStatement);
			JdbcUtils.closeQuietly(connection);
		}
		//return customer;
	}

	private Customer parseResultSet(ResultSet resultSet) throws SQLException {
		Customer customer = new Customer(resultSet.getString("username"), resultSet.getString("password"));
		customer.setId(resultSet.getInt("id"));
		return customer;
	}
}
