package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.entity.Customer;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class PostgreSqlCustomerDao implements CustomerDao {
	private JdbcTemplate jdbcTemplate;

	public PostgreSqlCustomerDao(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Customer cus){
		String sql = "insert into users values (default, ?, ?, ?, ?, ?, ?);";
		String sql1 = "insert into user_roles values ((select id from users where email = ?), 3);"; //insert always customer role
		jdbcTemplate.update(sql, cus.getName(), cus.getAddress(), cus.getLogin(), cus.getPassword(), cus.getEmail(), cus.getPayment());
		jdbcTemplate.update(sql1, cus.getEmail());
	}

	@Override
	public Customer read(String login){
		String sql = "select * from users where login = ?;";
		List<Customer> lc = jdbcTemplate.query(sql, new Object[] {login}, ROW_MAPPER_CUST);
		if (lc.size() > 0) {
			return lc.get(0);

		}
		else
			return null;
	}

	@Override
	public Customer readById(int id) {
		String sql = "select * from users where id = ?;";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, ROW_MAPPER_CUST);
	}

	@Override
	public void update(Customer cus){
		String sql = "update users set name = ?, address = ?, login = ?, email = ?, payment = ? where id = ?;";
		jdbcTemplate.update(sql, cus.getName(), cus.getAddress(), cus.getLogin(), cus.getEmail(), cus.getPayment(), cus.getId());
	}

	@Override
	public List<Customer> getAll() {
		String sql = "select * from users";
		return jdbcTemplate.query(sql, ROW_MAPPER_CUST);
	}

	@Override
	public void delete(String email) {
		String sql = "delete from users where email = ?;";
		String sql1 = "select * from users where email = ?;";
		String sql2 = "delete from cart where customer_id = ?;";
		String sql3 = "delete from orders where customer_id = ?;";

		Customer cus = jdbcTemplate.queryForObject(sql1, ROW_MAPPER_CUST, email);
        jdbcTemplate.update(sql2, cus.getId());
        jdbcTemplate.update(sql3, cus.getId());
        jdbcTemplate.update(sql, email);
	}

	@Override
	public boolean isAdmin(Customer customer) throws SQLException {

		String sql = "select r.role from roles r, user_roles ur, users u " +
				"where r.id = ur.role_id and ur.user_id = u.id and u.login = ?;";
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, customer.getLogin());
		ResultSet rs = ps.executeQuery();
		rs.next();

		String s = rs.getString("role");

		if (s.equalsIgnoreCase("ADMIN")) {
			System.out.println("ADMIN IN DA HOUSE - " + customer.getLogin());
			return true;
		}
		else {
			System.out.println("ITS CUSTOMER BABY - " + customer.getLogin());
			return false;
		}
	}
}
