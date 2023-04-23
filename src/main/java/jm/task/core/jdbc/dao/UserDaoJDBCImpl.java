package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String sqlQuerySaveUser = "insert into users (nameBD, lastNameBD, ageBD) values (?, ?, ?)";
    private final String sqlQueryCreateUsersTable = """
            CREATE TABLE IF NOT EXISTS users
            (
                IdBD INT AUTO_INCREMENT PRIMARY KEY,
                nameBD VARCHAR(45) NOT NULL,
                lastNameBD VARCHAR(64) NOT NULL,
                ageBD INT DEFAULT NULL
              ); \s""";
    private final String sqlQueryDropUsersTable = "drop table if exists users";
    private final String sqlQueryRemoveUserById = "delete from users where idBD =?";
    private final String sqlQueryGetAllUsers = "select * from users";
    private final String sqlQueryCleanUsersTable = "TRUNCATE TABLE users";

    public UserDaoJDBCImpl() {

    }

    //Создание табл.
    public void createUsersTable() throws SQLException {
        Connection connect = Util.getConnection();
        Statement statement = connect.createStatement();
        statement.executeUpdate(sqlQueryCreateUsersTable);
    }

    //Удаление табл.
    public void dropUsersTable() throws SQLException {
        Connection connect = Util.getConnection();
        Statement statement = connect.createStatement();
        statement.executeUpdate(sqlQueryDropUsersTable);
    }

    //Сохранение
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connect = Util.getConnection();
        PreparedStatement preparedStatement = connect.prepareStatement(sqlQuerySaveUser);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);
        preparedStatement.executeUpdate();
    }

    //Выборочное удаление
    public void removeUserById(long id) throws SQLException {
        Connection connect = Util.getConnection();
        PreparedStatement preparedStatement = connect.prepareStatement(sqlQueryRemoveUserById);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    //Смотреть таблицу
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connect = Util.getConnection();
        Statement statement = connect.createStatement();
        ResultSet result = statement.executeQuery(sqlQueryGetAllUsers);
        while (result.next()) {
            User user = new User();
            user.setId(result.getLong("idBD"));
            user.setName(result.getString("nameBD"));
            user.setLastName(result.getString("lastNameBD"));
            user.setAge(result.getByte("ageBD"));
            userList.add(user);
        }
        return userList;
    }

    //Очистить таблицу
    public void cleanUsersTable() throws SQLException {
        Connection connect = Util.getConnection();
        Statement statement = connect.createStatement();
        statement.executeUpdate(sqlQueryCleanUsersTable);
    }
}