package com.ncproject.webstore.ejb.beans;

import com.ncproject.webstore.dao.ProductDao;
import com.ncproject.webstore.dao.postgreSql.PostgreSqlProductDao;
import com.ncproject.webstore.ejb.ProductBeanInterface;
import com.ncproject.webstore.entity.Product;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.sql.DataSource;

import java.util.List;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

/**
 * Created by Черный on 01.03.2017.
 */
@TransactionAttribute(REQUIRES_NEW)
@Stateful
public class ProductBean implements ProductBeanInterface {
    @Override
    public List<Product> getAllProducts(DataSource dataSource) {
        ProductDao productDao = new PostgreSqlProductDao(dataSource);
        return productDao.getAllProducts();
    }

    @Override
    public List<Product> searchProducts(String productName, DataSource dataSource) {
        ProductDao productDao = new PostgreSqlProductDao(dataSource);
        return productDao.searchProducts(productName);
    }

    @Override
    public Product getProductById(int idString, DataSource dataSource) {
        ProductDao productDao = new PostgreSqlProductDao(dataSource);
        return productDao.getProductById(idString);
    }

    @Override
    public void createProduct(Product newProduct, DataSource dataSource) {
        ProductDao productDao = new PostgreSqlProductDao(dataSource);
        productDao.createProduct(newProduct);
    }

    @Override
    public void updateProduct(Product theProduct, DataSource dataSource) {
        ProductDao productDao = new PostgreSqlProductDao(dataSource);
        productDao.updateProduct(theProduct);
    }

    @Override
    public void deleteProduct(int id, DataSource dataSource) {
        ProductDao productDao = new PostgreSqlProductDao(dataSource);
        productDao.deleteProduct(id);
    }
}
