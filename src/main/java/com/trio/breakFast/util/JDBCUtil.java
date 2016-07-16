package com.trio.breakFast.util;


import java.sql.*;

/**
 * Created by cfw on 2014/8/19.
 */
public class JDBCUtil {


    // 创建静态全局变量
    static Connection conn;

    static PreparedStatement pst;

    /* 获取数据库连接的函数*/
    public static Connection getConnection() {
        Connection con = null;  //创建用于连接数据库的Connection对象
        try {
            Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ihome?useUnicode=true&characterEncoding=utf8", "ihome", "0401sima$@#");// 创建数据连接

        } catch (Exception e) {
            System.out.println("数据库连接失败" + e.getMessage());
        }
        return con; //返回所建立的数据库连接
    }


    public static int inserSmssent(String sql, String tplname, String smsto, String smscontent) {

        conn = getConnection(); // 首先要获取连接，即连接到数据库
        try {
            //"INSERT INTO tsmssent(tplname, smsto, smscontent, senddate) VALUES (?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);    // 创建用于执行静态sql语句的Statement对象
            pst.setString(1, tplname);
            pst.setString(2, smsto);
            pst.setString(3, smscontent);
            pst.setDate(4, new Date(System.currentTimeMillis()));
            int count = pst.executeUpdate();  // 执行插入操作的sql语句，并返回插入数据的个数

            System.out.println("向Tsmssent表中插入 " + count + " 条数据"); //输出插入操作的处理结果

            conn.close();   //关闭数据库连接
            return count;
        } catch (SQLException e) {
            System.out.println("插入数据失败" + e.getMessage());
        }
        return 0;
    }


}
