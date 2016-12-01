package myStore.DAO.postgre;

import myStore.DAO.CatalogDAO;
import myStore.DAO.DaoFactory;
import myStore.DAO.JdbcUtils;
import myStore.DAO.POJO.storeCatalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PostgreCatalogDAO implements CatalogDAO{
    private DaoFactory daoFactory = DaoFactory.getInstance();
//
//
    public storeCatalog read() throws Exception {
        //return only 1st line from base
        String sql = "select * from categories;";

        storeCatalog storeCatalog = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = daoFactory.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                storeCatalog = parseResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new Exception("Cannot read user", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
        if (null == storeCatalog) {
            System.out.println("Catalog not found");
        } else {
            System.out.println("Catalog found");
        }
        System.out.println("Returning catalog");
        return storeCatalog;
    }

    public List<storeCatalog> getAll() throws Exception{

        String sql = "select * from categories;";
        List<storeCatalog> sc = new ArrayList<storeCatalog>();
        storeCatalog storeCatalog = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = daoFactory.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                storeCatalog = parseResultSet(resultSet);
                sc.add(storeCatalog);
            }
        } catch (SQLException e) {
            throw new Exception("Cannot read user", e);
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
        if (null == storeCatalog) {
            System.out.println("Catalog not found");
        } else {
            System.out.println("Catalog found");
        }
        System.out.println("Returning catalog");
        return sc;
    }


    private storeCatalog parseResultSet(ResultSet resultSet) throws SQLException {

        storeCatalog storeCatalog = new storeCatalog(resultSet.getInt("id"), resultSet.getString("name"));
//        catalog.setCreditCardInfo(resultSet.getString("credit_card"));
//        catalog.setAddress(resultSet.getString("address"));
//        catalog.setPhone(resultSet.getString("phone"));
//        catalog.setId(resultSet.getInt("id"));
        return storeCatalog;
    }
}
