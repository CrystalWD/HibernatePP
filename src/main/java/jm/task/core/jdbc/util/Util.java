package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String DB_URL = "jdbc:mysql://localhost:3306/firsttestbd";
    private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "18273645q";

    /**
     * Метод для коннекта
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

}
