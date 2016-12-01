package myStore.DAO;

import myStore.DAO.POJO.storeCatalog;

/**
 * Created by One on 24.11.2016.
 */
public interface CatalogDAO {
    storeCatalog read() throws Exception;
}
