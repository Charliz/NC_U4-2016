package com.ncproject.webstore.ejb;

import com.ncproject.webstore.entity.CartWithNames;
import com.ncproject.webstore.entity.StoreCatalog;

import javax.ejb.Remote;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Черный on 01.03.2017.
 */
@Remote
public interface CatalogBeanInterface {
    List<StoreCatalog> getAll();
    List<CartWithNames> getByCustomerId(int id);
}
