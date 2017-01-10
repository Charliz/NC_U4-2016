package com.ncproject.webstore.dao;

import com.ncproject.webstore.dao.POJO.CartWithNames;
import com.ncproject.webstore.dao.POJO.storeCatalog;

import java.util.List;

/**
 * Created by One on 24.11.2016.
 */
public interface CatalogDAO {
    storeCatalog read() throws Exception;
    List<storeCatalog> getAll() throws Exception;
    List<CartWithNames> getByCustomerId(int id) throws Exception;
}
