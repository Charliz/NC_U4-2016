package com.ncproject.webstore.dao;

import com.ncproject.webstore.dao.POJO.CartWithNames;
import com.ncproject.webstore.dao.POJO.StoreCatalog;

import java.util.List;

/**
 * Created by One on 24.11.2016.
 */
public interface CatalogDAO {
    StoreCatalog read() throws Exception;
    List<StoreCatalog> getAll() throws Exception;
    List<CartWithNames> getByCustomerId(int id) throws Exception;
}
