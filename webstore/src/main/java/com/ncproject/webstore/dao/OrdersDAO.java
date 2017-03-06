package com.ncproject.webstore.dao;

import com.ncproject.webstore.entity.Orders;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by book on 01.01.2017.
 */
public interface OrdersDAO {
    RowMapper<Orders> ROW_MAPPER_ORD = (ResultSet resultSet, int rowNum) -> {
        return new Orders(resultSet.getInt("id"), resultSet.getInt("customer_id"),
                resultSet.getTimestamp("data"), resultSet.getString("status"),
                resultSet.getArray("product_list"), resultSet.getDouble("total"));
    };
    List<Orders> readById(int id) throws Exception;
    void createOrder(int customer_id) throws Exception;
    void setDataSource();
    List<Orders> getAllOrders();
}
