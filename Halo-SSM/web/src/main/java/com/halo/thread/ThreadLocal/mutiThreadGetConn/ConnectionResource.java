package com.halo.thread.ThreadLocal.mutiThreadGetConn;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

// 资源类
public class ConnectionResource {
    public static String userName = "root";
    public static String userPass = "123456";
    public static String url = "jdbc:mysql://127.0.0.1:3306/db0?useUnicode=true&amp&characterEncoding=UTF-8";
    public static String driverName = "com.mysql.jdbc.Driver";

    public Connection getCollection() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, userName, userPass);
        } catch (SQLException e) {
            System.out.println("获取数据库链接失败..." + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("获取数据库加载类失败..." + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }


}
