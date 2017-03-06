package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.ProductDao;
import com.ncproject.webstore.dao.rowMapper.ProductRowMapper;
import com.ncproject.webstore.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class PostgreSqlProductDao implements ProductDao {
    private JdbcTemplate jdbcTemplate;

    public PostgreSqlProductDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products ORDER BY id\n" + "ASC";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public List<Product> searchProducts(String productName) {
        String ename = "%" + productName + "%";
        String sql = "SELECT * FROM products WHERE LOWER(name) LIKE LOWER(?) OR LOWER(brand) LIKE LOWER(?) ";
        return jdbcTemplate.query(sql, new ProductRowMapper(), ename, ename);
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());
    }

    @Override
    public void createProduct(Product theProduct) {
        String sql = "INSERT INTO products (description, name, price, quantity, brand) "
                + "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, theProduct.getDescription(), theProduct.getProductName(),
                theProduct.getPrice(), theProduct.getQuantity(), theProduct.getBrand());
    }

    @Override
    public void updateProduct(Product theProduct) {
        String sql = "UPDATE products "
                + "SET description=?, name=?, price=?, quantity=?, brand=? "
                + "WHERE id=?";
        jdbcTemplate.update(sql, new Object[] {theProduct.getDescription(), theProduct.getProductName(),
                theProduct.getPrice(), theProduct.getQuantity(), theProduct.getBrand(), theProduct.getProd_id()});
    }

    @Override
    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
