package com.ncproject.webstore.dao.postgreSql;

import com.ncproject.webstore.dao.JdbcUtils;
import com.ncproject.webstore.dao.OrdersDAO;
import com.ncproject.webstore.dao.POJO.Orders;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by book on 01.01.2017.
 */
public class PostgreOrdersDAO implements OrdersDAO {

    private DataSource dataSource = null;
    private JdbcTemplate jdbcTemplate;

    public PostgreOrdersDAO(DataSource dataSource){
        this.dataSource = dataSource;
        setDataSource();
    }

    public void setDataSource() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Orders> readById(int id){

        String sql = "select * from orders where customer_id=?;";

        return jdbcTemplate.query(sql, new Object[] {id}, ROW_MAPPER_ORD);
    }

    @Override
    public void createOrder(int customer_id) throws SQLException {

        String sql = "insert into orders(customer_id, data, product_list, total, status) " +
                "values(?, now(), ?, (select sum(summary) from cart_sum where cart_id in " +
                "(select id from cart where customer_id = ?)), ?);";

        String sql2 = "select p.name, p.id from products p, cart c " +
                "where c.customer_id = ? and c.product_id = p.id;";

        String sql3 = "update products set quantity = (select quantity from products where id = ?) -1 where id = ?;";

        System.out.println("Start create order");
        List<String> myList = new ArrayList<String>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql2, new Object[] {customer_id});
        for (Map row : rows) {
            myList.add((String) row.get("name"));
        }
        Object[] myobjArray = myList.toArray();
        Connection connection = dataSource.getConnection();

        Array myArray = connection.createArrayOf("text", myobjArray);

        jdbcTemplate.update(sql, preparedStatement -> {
            int i = 1;
            preparedStatement.setInt(i++, customer_id);
            preparedStatement.setArray(i++, myArray);
            preparedStatement.setInt(i++, customer_id);
            preparedStatement.setString(i++, "not ready");
        });

        System.out.println("insert into orders successfull");
    }
}
