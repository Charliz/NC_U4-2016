package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.CartDAO;
import com.ncproject.webstore.dao.JdbcUtils;
import com.ncproject.webstore.entity.Cart;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PostgreCartDAO implements CartDAO{
    private DataSource dataSource = null;

    public PostgreCartDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Cart> readById(int id){

        String sql = "select * from cart where customer_id = ?;";

        List<Cart> cart = new ArrayList<Cart>();
        Cart myCart = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                myCart = parseResultSet(resultSet);
                cart.add(myCart);
            }
        } catch (SQLException e) {
            System.out.println("Cannot read cart");
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
        if (null == cart) {
            System.out.println("Cart not found");
        } else {
            System.out.println("Cart found");
        }
        System.out.println("Returning cart");
        return cart;
    }

    public void addToCart(int customer_id, int product_id) {

        String sql = "insert into cart (customer_id, product_id, count) values (?, ?, ?);";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer_id);
            preparedStatement.setInt(2, product_id);
            preparedStatement.setInt(3, 1);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Cannot insert into cart");
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
    }

    public String getCartSumById(int id){

        String sql = "select SUM (summary) as sum from cart_sum where cart_id in (select id from cart where customer_id = ?);";

        Double d = .0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                d = resultSet.getDouble("sum");
            }
        } catch (SQLException e) {
            System.out.println("Cannot read cart sum");
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
        if (.0 == d) {
            System.out.println("Cart sum not found");
        } else {
            System.out.println("Cart sum found");
        }
        return d.toString();
    }

    public void delFromCart(int id){

        String sql = "delete from cart where id = ?;";

        System.out.println("Start delete");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Cannot insert into cart");
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
    }


    private Cart parseResultSet(ResultSet resultSet) throws SQLException {

        Cart cart = new Cart(resultSet.getInt("id"), resultSet.getInt("customer_id"),
                resultSet.getInt("product_id"), resultSet.getInt("count"));

        return cart;
    }
}
