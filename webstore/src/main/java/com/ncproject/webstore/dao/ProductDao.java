package com.ncproject.webstore.dao;


import com.ncproject.webstore.entity.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAllProducts() throws Exception;
    Product getProductById(String idString) throws Exception;
    void createProduct(Product newProduct) throws Exception;
    void updateProduct(Product theProduct) throws Exception;
    void deleteProduct(String idString) throws Exception;

}

