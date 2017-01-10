package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.CartDAO;
import com.ncproject.webstore.dao.DaoFactory2;
import com.ncproject.webstore.dao.JdbcUtils;
import com.ncproject.webstore.dao.POJO.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PostgreCartDAO implements CartDAO{

    private DaoFactory2 daoFactory = DaoFactory2.getInstance();

    public List<Cart> readById(int id) throws Exception {
        String sql = "select * from cart where customer_id=?;";

        List<Cart> cart = new ArrayList<Cart>();
        Cart myCart = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = daoFactory.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                myCart = parseResultSet(resultSet);
                cart.add(myCart);
            }
        } catch (SQLException e) {
            throw new Exception("Cannot read cart", e);
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

    public void AddToCart(String id) throws Exception {

        String sql = "insert into cart (customer_id, product_id, count) values (?, ?, ?);";
        System.out.println("Start insert");
        Double d = .0;
        int idInt = Integer.parseInt(id);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = daoFactory.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 3);
            preparedStatement.setInt(2, idInt);
            preparedStatement.setInt(3, 1);
            preparedStatement.execute();
//            if (resultSet.next()) {
//                d = resultSet.getDouble("sum");
//            }
        } catch (SQLException e) {
            throw new Exception("Cannot insert into cart", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }

        System.out.println("insert into cart successfull");
    }

    public String getCartSumById(int id) throws Exception {

        String sql = "select SUM (summary) as sum from cart_sum where cart_id in (select id from cart where customer_id = ?);";

        Double d = .0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = daoFactory.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                d = resultSet.getDouble("sum");
            }
        } catch (SQLException e) {
            throw new Exception("Cannot read cart sum", e);
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
        System.out.println("Returning cart sum = " + d.toString());
        return d.toString();
    }

    public void DelFromCart(int id) throws Exception {
        String sql = "delete from cart where id = ?;";
        System.out.println("Start delete");
//        int idInt = Integer.parseInt(id);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = daoFactory.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new Exception("Cannot insert into cart", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }

        System.out.println("insert into cart successfull");
    }


    private Cart parseResultSet(ResultSet resultSet) throws SQLException {

        Cart cart = new Cart(resultSet.getInt("id"), resultSet.getInt("customer_id"),
                resultSet.getInt("product_id"), resultSet.getInt("count"));

        return cart;
    }
}
