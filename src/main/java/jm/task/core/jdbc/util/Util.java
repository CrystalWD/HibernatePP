package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String DB_URL = "jdbc:mysql://localhost:3306/firsttestbd";
    private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "18273645q";

    //Метод для коннекта
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    //Коннект hybernate
    public static SessionFactory getSessionFactory() throws HibernateException {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", DB_DRIVER)
                    .setProperty("hibernate.connection.url", DB_URL)
                    .setProperty("hibernate.connection.username", DB_USERNAME)
                    .setProperty("hibernate.connection.password", DB_PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                    .setProperty("hibernate.current_session_context_class", "thread")
                    .setProperty("hibernate.hbm2ddl.auto", "none")
                    .addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
