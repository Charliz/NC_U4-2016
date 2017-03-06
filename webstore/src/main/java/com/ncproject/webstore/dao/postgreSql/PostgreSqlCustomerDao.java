package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.CustomerDao;
import com.ncproject.webstore.entity.Customer;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
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
		return jdbcTemplate.queryForObject(sql, ROW_MAPPER_CUST, login);
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
}
