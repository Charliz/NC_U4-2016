package com.ncproject.webstore.dao.rowMapper;

import com.ncproject.webstore.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;



public class ProductRowMapper implements RowMapper<Product>{

    @Override
    public Product mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Product product = new Product();
        product.setProd_id(resultSet.getInt("id"));
        product.setDescription(resultSet.getString("description"));
        product.setProductName(resultSet.getString("name"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setBrand(resultSet.getString("brand"));

        return product;
    }

}
