package com.ncproject.webstore.ejb.beans;

import com.ncproject.webstore.dao.CatalogDAO;
import com.ncproject.webstore.entity.CartWithNames;
import com.ncproject.webstore.entity.StoreCatalog;
import com.ncproject.webstore.dao.postgreSql.PostgreCatalogDAO;
import com.ncproject.webstore.ejb.CatalogBeanInterface;

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
public class CatalogBean implements CatalogBeanInterface {
    @Override
    public List<StoreCatalog> getAll(DataSource dataSource) {
        CatalogDAO catalogDAO = new PostgreCatalogDAO(dataSource);
        return catalogDAO.getAll();
    }

    @Override
    public List<CartWithNames> getByCustomerId(int id, DataSource dataSource) {
        CatalogDAO catalogDAO = new PostgreCatalogDAO(dataSource);
        return catalogDAO.getByCustomerId(id);
    }
}
