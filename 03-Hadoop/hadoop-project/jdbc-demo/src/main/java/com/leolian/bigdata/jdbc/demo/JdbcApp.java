package com.leolian.bigdata.jdbc.demo;

import com.leolian.bigdata.jdbc.demo.service.JdbcService;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/15 10:15
 */
public class JdbcApp {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/lian";
        String username = "root";
        String password = "123456";
        JdbcService jdbcService = new JdbcService(url, username, password);
        jdbcService.call();
    }

}
