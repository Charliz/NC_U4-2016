package com.ncproject.webstore.dao.PostgreSql;

import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.dao.DaoFactory;
import com.ncproject.webstore.dao.JdbcUtils;
import com.ncproject.webstore.entity.Customer;

import java.sql.*;


public class PostgreSqlCustomerDao implements CustomerDao {
	private DaoFactory daoFactory = DaoFactory.getInstance();

	@Override
	public void create(Customer customer){
		String sql = "insert into customer values (default, ?, ?, ?, ?, ?, ?);";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getAddress());
			preparedStatement.setString(3, customer.getLogin());
			preparedStatement.setString(4, customer.getPassword());
			preparedStatement.setString(5, customer.getEmail());
			preparedStatement.setString(6, customer.getPayment());
			preparedStatement.execute();
		} catch (SQLException e) {
			System.out.println("Customer insert exception");
		} finally {
			JdbcUtils.closeQuietly(preparedStatement);
			JdbcUtils.closeQuietly(connection);
		}
	}

	@Override
	public Customer read(String login){
		String sql = "select * from customer where login = ?;";

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
			System.out.println("Customer select exception");
		} finally {
			JdbcUtils.closeQuietly(resultSet);
			JdbcUtils.closeQuietly(preparedStatement);
			JdbcUtils.closeQuietly(connection);
		}
		return customer;
	}

	@Override
	public void update(Customer customer){

		String sql = "update customer set name = ?, address = ?, login = ?, password = ?, email = ?, payment = ? where id = 4;";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getAddress());
			preparedStatement.setString(3, customer.getLogin());
			preparedStatement.setString(4, customer.getPassword());
			preparedStatement.setString(5, customer.getEmail());
			preparedStatement.setString(6, customer.getPayment());
			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
		} catch (SQLException e) {
			System.out.println("Customer update exception");
		} finally {
			JdbcUtils.closeQuietly(resultSet);
			JdbcUtils.closeQuietly(preparedStatement);
			JdbcUtils.closeQuietly(connection);
		}
	}

	private Customer parseResultSet(ResultSet resultSet) throws SQLException {
		Customer customer = new Customer(resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("payment"));
		customer.setId(resultSet.getInt("id"));
		return customer;
	}
}
