package com.ncproject.webstore.ejb;

import com.ncproject.webstore.dao.POJO.CartWithNames;
import com.ncproject.webstore.dao.POJO.StoreCatalog;

import javax.ejb.Local;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Черный on 01.03.2017.
 */
@Local
public interface CatalogBeanInterface {
    List<StoreCatalog> getAll(DataSource dataSource);
    List<CartWithNames> getByCustomerId(int id, DataSource dataSource);
}
