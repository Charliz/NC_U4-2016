package com.ncproject.webstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
//    private static Logger log = Logger.getLogger(JdbcUtils.class.getName());

    private JdbcUtils() {
    }

    public static void closeQuietly(ResultSet rs) {
        // реализует итератор
        if (rs != null) {
            try {
                rs.close();
                System.out.println("result set closed");
            } catch (SQLException e) {
                System.out.println("Cannot close result set");
                // NOP
            }
        }
    }

    public static void closeQuietly(Statement ps) {
        if (ps != null) {
            try {
                ps.close();
                System.out.println("statement closed");
            } catch (SQLException e) {
                System.out.println("Cannot close statement");
                // NOP
            }
        }
    }

    public static void closeQuietly(Connection conn) {
        // закрывается физическое соединенние
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                System.out.println("Cannot close connection");
                // NOP
            }
        }
    }

    public static void rollbackQuietly(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                // NOP
            }
        }
    }
}
