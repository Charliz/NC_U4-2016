package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.DaoFactory2;
import com.ncproject.webstore.dao.JdbcUtils;
import com.ncproject.webstore.dao.OrdersDAO;
import com.ncproject.webstore.dao.POJO.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by book on 01.01.2017.
 */
public class PostgreOrdersDAO implements OrdersDAO {

    private DaoFactory2 daoFactory = DaoFactory2.getInstance();

    @Override
    public List<Orders> readById(int id) throws Exception {

        String sql = "select * from orders where customer_id=?;";

        List<Orders> lOrders = new ArrayList<Orders>();
        Orders orders = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = daoFactory.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders = parseResultSet(resultSet);
                lOrders.add(orders);
            }
        } catch (SQLException e) {
            throw new Exception("Cannot read orders", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
        if (null == orders) {
            System.out.println("Orders not found");
        } else {
            System.out.println("Orders found");
        }
        System.out.println("Returning orders");
        return lOrders;
    }

    @Override
    public void createOrder(int customer_id) throws Exception {

        String sql = "insert into orders(customer_id, data, product_list, total, status) " +
                "values(?, now(), ?, (select sum(summary) from cart_sum where cart_id in " +
                "(select id from cart where customer_id = ?)), ?);";

        String sql2 = "select p.name, p.id from products p, cart c " +
                "where c.customer_id = ? and c.product_id = p.id;";
        System.out.println("Start insert into orders");
        Double d = .0;
//        int idInt = Integer.parseInt(customer_id);
        Array myArray = null;
        ArrayList<String> myList = new ArrayList<String>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet = null;
        ResultSet rs2 = null;
        try {

            connection = daoFactory.getConnection();

            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, customer_id);
            rs2 = preparedStatement2.executeQuery();
            while (rs2.next()) {

                myList.add(rs2.getString("name"));
            }
            Object[] myobjArray = myList.toArray();
            myArray = connection.createArrayOf("text", myobjArray);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer_id);
            preparedStatement.setArray(2, myArray);
            preparedStatement.setInt(3, customer_id);
            preparedStatement.setString(4, "not ready");
            preparedStatement.execute();
//            if (resultSet.next()) {
//                d = resultSet.getDouble("sum");
//            }
        } catch (SQLException e) {
            throw new Exception("Cannot insert into orders", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }

        System.out.println("insert into orders successfull");
    }

    private Orders parseResultSet(ResultSet resultSet) throws SQLException {

        Orders orders = new Orders(resultSet.getInt("id"), resultSet.getInt("customer_id"),
                resultSet.getTimestamp("data"), resultSet.getString("status"),
                resultSet.getArray("product_list"), resultSet.getDouble("total"));

        return orders;
    }
}
