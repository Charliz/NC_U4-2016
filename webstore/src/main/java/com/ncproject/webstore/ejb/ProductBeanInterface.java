package com.ncproject.webstore.ejb;

import com.ncproject.webstore.entity.Product;

import javax.ejb.Local;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Черный on 01.03.2017.
 */
@Local
public interface ProductBeanInterface {
    List<Product> getAllProducts(DataSource dataSource);
    List<Product> searchProducts(String productName, DataSource dataSource);
    Product getProductById(int idString, DataSource dataSource);
    void createProduct(Product newProduct, DataSource dataSource);
    void updateProduct(Product theProduct, DataSource dataSource);
    void deleteProduct(int id, DataSource dataSource);
}
