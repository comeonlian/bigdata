package com.leolian.bigdata.jdbc.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/15 10:15
 */
public class MysqlDao {
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    
    private ThreadLocal<Connection> connections = new ThreadLocal<>();
    
    private String url;
    private String username;
    private String password;

    public MysqlDao(String url, String username, String password) {
        try {
            this.url = url;
            this.username = username;
            this.password = password;
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection initConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Connection getConnection() {
        Connection connection = connections.get();
        if (null == connection) {
            connection = initConnection();
            if (null == connection) {
                return null;
            }
            connections.set(connection);
        }
        return connection;
    }
    
}
