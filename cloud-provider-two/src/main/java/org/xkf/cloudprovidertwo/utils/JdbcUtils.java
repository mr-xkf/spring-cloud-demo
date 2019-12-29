/**
 * FileName: JdbcUtils
 * Author:   13235
 * Date:     2019/10/14 22:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br>
 * jdbc工具类
 *
 * @author 13235
 * @create 2019/10/14
 * @since 1.0.0
 */
public class JdbcUtils {

    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/test";
    private static Connection connection;

    static {
        try {
            Class.forName("com.jdbc.mysql.driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 获取游标结果集
     *
     * @param sql      sql语句
     * @param startNo  开始数
     * @param maxCount 最大的条数
     * @return
     */
    public static ResultSet getResultSet(String sql, int startNo, int maxCount) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setMaxRows(maxCount);
            resultSet = preparedStatement.executeQuery();
            //游标起始位
            resultSet.first();
            resultSet.absolute(startNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    /***
     *
     *  释放连接
     *
     */
    public static void releaseConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
