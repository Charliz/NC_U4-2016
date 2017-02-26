package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.CatalogDAO;
import com.ncproject.webstore.dao.JdbcUtils;
import com.ncproject.webstore.dao.POJO.CartWithNames;
import com.ncproject.webstore.dao.POJO.StoreCatalog;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PostgreCatalogDAO implements CatalogDAO{
    private DataSource dataSource = null;

    public PostgreCatalogDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public StoreCatalog read() throws Exception {
        //return only 1st line from base
        String sql = "select * from cart;";

        StoreCatalog StoreCatalog = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                StoreCatalog = parseResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new Exception("Cannot read user", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
        if (null == StoreCatalog) {
            System.out.println("Catalog not found");
        } else {
            System.out.println("Catalog found");
        }
        System.out.println("Returning catalog");
        return StoreCatalog;
    }

    public List<StoreCatalog> getAll() throws Exception{

        String sql = "select * from products;";
        List<StoreCatalog> sc = new ArrayList<StoreCatalog>();
        StoreCatalog StoreCatalog = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                StoreCatalog = parseResultSet(resultSet);
                sc.add(StoreCatalog);
            }
        } catch (SQLException e) {
            throw new Exception("Cannot read user", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
        if (null == StoreCatalog) {
            System.out.println("Catalog not found");
        } else {
            System.out.println("Catalog found");
        }
        System.out.println("Returning catalog");
        return sc;
    }

    public List<CartWithNames> getByCustomerId(int id) throws Exception{
        String sql = "select c.id, p.name, c.count, s.summary " +
                "from products p, cart c, cart_sum s " +
                "where p.id = c.product_id and c.id = s.cart_id and c.customer_id = ?;";

        CartWithNames cwn = null;
        List<CartWithNames> sc = new ArrayList<CartWithNames>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                cwn = new CartWithNames(resultSet.getInt("id") ,resultSet.getString("name"),
                                    resultSet.getInt("count"), resultSet.getDouble("summary"));
                sc.add(cwn);
            }
        } catch (SQLException e) {
            throw new Exception("Cannot get catalog for cart", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
        if (null == cwn) {
            System.out.println("Catalog not found");
        } else {
            System.out.println("Catalog found");
        }
        System.out.println("Returning catalog cart");
        return sc;
    }

    private StoreCatalog parseResultSet(ResultSet resultSet) throws SQLException {

        StoreCatalog StoreCatalog = new StoreCatalog(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getString("description"), resultSet.getString("price"), resultSet.getInt("quantity"));
//        catalog.setCreditCardInfo(resultSet.getString("credit_card"));
//        catalog.setAddress(resultSet.getString("address"));
//        catalog.setPhone(resultSet.getString("phone"));
//        catalog.setId(resultSet.getInt("id"));
        return StoreCatalog;
    }
}
