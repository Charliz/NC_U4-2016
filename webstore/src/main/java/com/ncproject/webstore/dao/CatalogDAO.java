package com.ncproject.webstore.dao;

import com.ncproject.webstore.entity.CartWithNames;
import com.ncproject.webstore.entity.StoreCatalog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by One on 24.11.2016.
 */
public interface CatalogDAO {

    RowMapper<StoreCatalog> ROW_MAPPER_SC = (ResultSet resultSet, int rowNum) -> {
        return new StoreCatalog(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getString("description"), resultSet.getString("price"), resultSet.getInt("quantity"));
    };
    RowMapper<CartWithNames> ROW_MAPPER_CWN = (ResultSet resultSet, int rowNum) -> {
        return new CartWithNames(resultSet.getInt("id") ,resultSet.getString("name"),
                resultSet.getInt("count"), resultSet.getDouble("summary"));
    };

    //    storeCatalog read() throws Exception;
    List<StoreCatalog> getAll();
    List<CartWithNames> getByCustomerId(int id);
}
