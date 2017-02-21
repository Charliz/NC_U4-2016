package com.ncproject.webstore.dao;


import com.ncproject.webstore.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface ProductDao {

    /*RowMapper<Product> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new Product(resultSet.getInt("id"),
            resultSet.getString("description"), resultSet.getString("name"),
            resultSet.getBigDecimal("price"), resultSet.getString("brand"));*/

    List<Product> getAllProducts();
    List<Product> searchProducts(String productName);
    Product getProductById(int idString);
    void createProduct(Product newProduct);
    void updateProduct(Product theProduct);
    void deleteProduct(int id);

}

