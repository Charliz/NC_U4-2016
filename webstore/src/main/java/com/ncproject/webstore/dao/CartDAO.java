package com.ncproject.webstore.dao;

import com.ncproject.webstore.entity.Cart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;


public interface CartDAO {
    RowMapper<Cart> ROW_MAPPER_C = (ResultSet resultSet, int rowNum) -> {
        return new Cart(resultSet.getInt("id"), resultSet.getInt("customer_id"),
                resultSet.getInt("product_id"), resultSet.getInt("count"));
    };

    List<Cart> readById(int id);
    void addToCart(int customer_id, int product_id);
    String getCartSumById(int id);
    void delFromCart(int id);
}
