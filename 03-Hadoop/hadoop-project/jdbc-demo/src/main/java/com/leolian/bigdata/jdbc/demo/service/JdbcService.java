package com.leolian.bigdata.jdbc.demo.service;

import com.leolian.bigdata.jdbc.demo.dao.MysqlDao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/15 10:29
 */
public class JdbcService {
    private MysqlDao mysqlDao;

    public JdbcService(String url, String username, String password) {
        mysqlDao = new MysqlDao(url, username, password);
    }

    /**
     * 统一调用
     * @throws Exception
     */
    public void call() throws Exception {
        Connection connection = mysqlDao.getConnection();
        connection.setAutoCommit(false);
        callProcedure(connection);
        callFunction(connection);
        connection.close();
    }

    /**
     * 调用存储过程
     * @param connection
     * @throws Exception
     */
    public void callProcedure(Connection connection) throws Exception {
        String sql = "{call sp_add(?, ?, ?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, 8679);
        callableStatement.setInt(2, 9898);
        callableStatement.registerOutParameter(3, Types.INTEGER);
        callableStatement.execute();
        long result = callableStatement.getInt(3);
        connection.commit();
        System.out.println("sp_add result: " + result);
    }

    /**
     * 调用函数
     * @param connection
     * @throws Exception
     */
    public void callFunction(Connection connection) throws Exception {
        String sql = "{? = call sf_add(?, ?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.setInt(2, 8979);
        callableStatement.setInt(3, 9999);
        callableStatement.execute();
        int result = callableStatement.getInt(1);
        connection.commit();
        System.out.println("sf_add result: " + result);
    }

}
