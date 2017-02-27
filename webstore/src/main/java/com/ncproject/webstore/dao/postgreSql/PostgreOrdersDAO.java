package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.JdbcUtils;
import com.ncproject.webstore.dao.OrdersDAO;
import com.ncproject.webstore.dao.POJO.Orders;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by book on 01.01.2017.
 */
public class PostgreOrdersDAO implements OrdersDAO {

    private DataSource dataSource = null;

    public PostgreOrdersDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Orders> getAllOrders() {

        List<Orders> ordersList = new ArrayList<>();
        Orders orders = null;

        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet resultSet = null;
        try {
            // get a connection
            myConnection = dataSource.getConnection();
            String sql = "SELECT * FROM orders ORDER BY id\n" +
                    "ASC";

            myStatement = myConnection.createStatement();
            resultSet = myStatement.executeQuery(sql);

            while (resultSet.next()) {
                orders = parseResultSet(resultSet);
                ordersList.add(orders);
            }
        } catch (SQLException e) {
                System.out.println("Cannot read orders");
        }

         finally {
            // close JDBC objects
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(myStatement);
            JdbcUtils.closeQuietly(myConnection);
        } if (null == orders) {
            System.out.println("Orders not found");
        } else {
            System.out.println("Orders found");
        }
        System.out.println("Returning orders");
        return ordersList;
    }

    @Override
    public List<Orders> readById(int id){

        String sql = "select * from orders where customer_id=?;";

        List<Orders> lOrders = new ArrayList<Orders>();
        Orders orders = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders = parseResultSet(resultSet);
                lOrders.add(orders);
            }
        } catch (SQLException e) {
            System.out.println("Cannot read orders");
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
    public void createOrder(int customer_id){

        String sql = "insert into orders(customer_id, data, product_list, total, status) " +
                "values(?, now(), ?, (select sum(summary) from cart_sum where cart_id in " +
                "(select id from cart where customer_id = ?)), ?);";

        String sql2 = "select p.name, p.id from products p, cart c " +
                "where c.customer_id = ? and c.product_id = p.id;";

        String sql3 = "update products set quantity = (select quantity from products where id = ?) -1 where id = ?;";

        System.out.println("Start create order");
        Double d = .0;
//        int idInt = Integer.parseInt(customer_id);
        Array myArray = null;
        ArrayList<String> myList = new ArrayList<String>();
        ArrayList<Integer> myListId = new ArrayList<Integer>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        ResultSet resultSet = null;
        ResultSet rs2 = null;

        Savepoint save1 = null;
        try {

            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            save1 = connection.setSavepoint();

            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, customer_id);
            rs2 = preparedStatement2.executeQuery();
            while (rs2.next()) {
                preparedStatement3 = connection.prepareStatement(sql3);
                preparedStatement3.setInt(1, rs2.getInt("id"));
                preparedStatement3.setInt(2, rs2.getInt("id"));
                preparedStatement3.execute();

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
            //connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(save1);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("Cannot insert into orders");
            e.printStackTrace();
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }

        System.out.println("Create order successfull");
    }

    private Orders parseResultSet(ResultSet resultSet) throws SQLException {

        Orders orders = new Orders(resultSet.getInt("id"), resultSet.getInt("customer_id"),
                resultSet.getTimestamp("data"), resultSet.getString("status"),
                resultSet.getArray("product_list"), resultSet.getDouble("total"));

        return orders;
    }
}
