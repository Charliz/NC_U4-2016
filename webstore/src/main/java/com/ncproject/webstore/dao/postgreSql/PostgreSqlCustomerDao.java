package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.dao.JdbcUtils;
import com.ncproject.webstore.entity.Customer;

import javax.sql.DataSource;
import java.sql.*;


public class PostgreSqlCustomerDao implements CustomerDao {
	private DataSource dataSource = null;

	public PostgreSqlCustomerDao(DataSource dataSource){
		this.dataSource = dataSource;
	}

	@Override
	public void create(Customer customer){
		String sql = "insert into users values (default, ?, ?, ?, ?, ?, ?);";
		String sql1 = "insert into user_roles values ((select id from users where email = ?), 3);"; //insert always customer role

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		Savepoint save1 = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			save1 = connection.setSavepoint();

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getAddress());
			preparedStatement.setString(3, customer.getLogin());
			preparedStatement.setString(4, customer.getPassword());
			preparedStatement.setString(5, customer.getEmail());
			preparedStatement.setString(6, customer.getPayment());
			preparedStatement.execute();

			preparedStatement1 = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			preparedStatement1.setString(1, customer.getEmail());
			preparedStatement1.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback(save1);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("Customer insert exception");
		} finally {
			JdbcUtils.closeQuietly(preparedStatement);
			JdbcUtils.closeQuietly(connection);
		}
	}

	@Override
	public Customer read(String login){
		String sql = "select * from users where login = ?;";

		Customer customer = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
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
	public Customer readById(int id){
		String sql = "select * from users where id = ?;";

		Customer customer = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
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

		String sql = "update users set name = ?, address = ?, login = ?, password = ?, email = ?, payment = ? where id = ?;";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getAddress());
			preparedStatement.setString(3, customer.getLogin());
			preparedStatement.setString(4, customer.getPassword());
			preparedStatement.setString(5, customer.getEmail());
			preparedStatement.setString(6, customer.getPayment());
			preparedStatement.setInt(7, customer.getId());
			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
		} catch (SQLException e) {
			System.out.println("Customer update exception");
			e.printStackTrace();
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
