package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.CartDAO;
import com.ncproject.webstore.dao.JdbcUtils;
import com.ncproject.webstore.dao.POJO.Cart;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PostgreCartDAO implements CartDAO{
    private JdbcTemplate jdbcTemplate;

    public PostgreCartDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Cart> readById(int id){
        String sql = "select * from cart where customer_id = ?;";
        return jdbcTemplate.query(sql, new Object[]{id}, ROW_MAPPER_C);
    }

    public void addToCart(int customer_id, int product_id) {
        String sql = "insert into cart (customer_id, product_id, count) values (?, ?, ?);";
        jdbcTemplate.update(sql, customer_id, product_id, 1);
    }

    public String getCartSumById(int id){
        String sql = "select SUM (summary) as sum from cart_sum where cart_id in (select id from cart where customer_id = ?);";
        String myStr = (String)jdbcTemplate.queryForObject(sql, String.class, new Object[]{id});

        System.out.println("my str is " + myStr);
        return (null == myStr) ? "0.0" : myStr;
    }

    public void delFromCart(int id){
        String sql = "delete from cart where id = ?;";

        System.out.println("Start delete");
        jdbcTemplate.update(sql, id);

        System.out.println("delete successfull");
    }
}
