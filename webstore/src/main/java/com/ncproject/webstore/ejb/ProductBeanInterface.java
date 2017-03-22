package com.ncproject.webstore.ejb;

import com.ncproject.webstore.entity.Product;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Черный on 01.03.2017.
 */
@Remote
public interface ProductBeanInterface {
    List<Product> getAllProducts();
    List<Product> searchProducts(String productName);
    Product getProductById(int idString);
    void createProduct(Product newProduct);
    void updateProduct(Product theProduct);
    void deleteProduct(int id);
}
