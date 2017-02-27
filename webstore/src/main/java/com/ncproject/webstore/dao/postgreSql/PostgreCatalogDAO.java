package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.CatalogDAO;
import com.ncproject.webstore.dao.JdbcUtils;
import com.ncproject.webstore.dao.POJO.CartWithNames;
import com.ncproject.webstore.dao.POJO.StoreCatalog;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.sql.XADataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PostgreCatalogDAO implements CatalogDAO{
    private DataSource dataSource = null;
    private JdbcTemplate jdbcTemplate;

    public PostgreCatalogDAO(DataSource dataSource){
        this.dataSource = dataSource;
        setDataSource();
    }

    public void setDataSource() {
        this.jdbcTemplate = new JdbcTemplate((DataSource)dataSource);
    }

    public List<StoreCatalog> getAll() throws Exception{

        return jdbcTemplate.query("select * from products;", ROW_MAPPER_SC);
    }

    public List<CartWithNames> getByCustomerId(int id) throws Exception{
        String sql = "select c.id, p.name, c.count, s.summary " +
                "from products p, cart c, cart_sum s " +
                "where p.id = c.product_id and c.id = s.cart_id and c.customer_id = ?;";


        return jdbcTemplate.query(sql, new Object[] {id}, ROW_MAPPER_CWN);

    }

}
