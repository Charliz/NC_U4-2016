package com.ncproject.webstore.dao.PostgreSql;

import com.ncproject.webstore.dao.DbExceptions;
import com.ncproject.webstore.dao.ProductDao;
import com.ncproject.webstore.entity.Product;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSqlProductDao implements ProductDao {

    private DataSource dataSource;

    public PostgreSqlProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAllProducts() throws Exception {

        List<Product> productList = new ArrayList();

        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet resultSet = null;

        try {
            // get a connection
            myConnection = dataSource.getConnection();
            String sql = "SELECT * FROM products ORDER BY prod_id\n" +
                    "ASC";
            myStatement = myConnection.createStatement();
            resultSet = myStatement.executeQuery(sql);

            while (resultSet.next()) {
                Product tempProduct = resultSetToProduct(resultSet);
                productList.add(tempProduct);
            }
            return productList;

        } finally {
            // close JDBC objects
            DbExceptions.close(resultSet);
            DbExceptions.close(myStatement);
            DbExceptions.close(myConnection);
        }
    }

    public void insertProduct(Product newProduct) throws Exception {
        Connection myConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            // get a connection
            myConnection = dataSource.getConnection();

            String sql = "INSERT INTO products (prod_id, category, description, prod_name, price, brand) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            // prepare statement
            preparedStatement = myConnection.prepareStatement(sql);

            // set parameters
            preparedStatement.setInt(1, newProduct.getProd_id());
            preparedStatement.setInt(2, newProduct.getCategory());
            preparedStatement.setString(3, newProduct.getDescription());
            preparedStatement.setString(4, newProduct.getProductName());
            preparedStatement.setBigDecimal(5, newProduct.getPrice());
            preparedStatement.setString(6, newProduct.getBrand());

            // execute SQL
            preparedStatement.executeUpdate();
        }
        finally {
            DbExceptions.close(preparedStatement);
            DbExceptions.close(myConnection);
        }
    }

    public Product getProductById(String idString) throws Exception {
        Product theProduct;

        Connection myConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            int prodId = Integer.parseInt(idString);
            myConnection = dataSource.getConnection();

            String sql = "SELECT * FROM products where prod_id = ?";

            // create prepared statement
            preparedStatement = myConnection.prepareStatement(sql);

            // set params
            preparedStatement.setInt(1, prodId);

            // execute statement
            resultSet = preparedStatement.executeQuery();

            // retreive data from the result set row
            if (resultSet.next()) {

                // use the product Id during construction
                theProduct = resultSetToProduct(resultSet);
            }
            else {
                throw new Exception("Could not find Product Id: " + prodId);
            }

            return theProduct;

        } finally {
            // close JDBC objects
            DbExceptions.close(resultSet);
            DbExceptions.close(preparedStatement);
            DbExceptions.close(myConnection);
        }
    }

    public void updateProduct(Product theProduct) throws Exception {
        Connection myConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            myConnection = dataSource.getConnection();
            String sql = "UPDATE products "
                    + "SET category=?, description=?, prod_name=?, price=?, brand=? "
                    + "WHERE prod_id=?";

            preparedStatement = myConnection.prepareStatement(sql);

            preparedStatement.setInt(1, theProduct.getCategory());
            preparedStatement.setString(2, theProduct.getDescription());
            preparedStatement.setString(3, theProduct.getProductName());
            preparedStatement.setBigDecimal(4, theProduct.getPrice());
            preparedStatement.setString(5, theProduct.getBrand());
            preparedStatement.setInt(6, theProduct.getProd_id());

            preparedStatement.execute();

        } finally {
            DbExceptions.close(preparedStatement);
            DbExceptions.close(myConnection);
        }

    }

    public void deleteProduct(String idString) throws Exception {

        Connection myConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            // convert product id to int
            int prodId = Integer.parseInt(idString);

            // get a connection to the DB
            myConnection = dataSource.getConnection();

            String sql = "DELETE FROM products WHERE prod_id=?";

            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setInt(1, prodId);

            preparedStatement.execute();

        }
        finally {
            DbExceptions.close(preparedStatement);
            DbExceptions.close(myConnection);
        }

    }

    // helper method
    private Product resultSetToProduct(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("prod_id");
        int category = myRs.getInt("category");
        String description = myRs.getString("description");
        String prod_name = myRs.getString("prod_name");
        BigDecimal price = myRs.getBigDecimal("price");
        String brand = myRs.getString("brand");

        Product tempProduct = new Product(id, category, description, prod_name, price, brand);
        return tempProduct;
    }


}

