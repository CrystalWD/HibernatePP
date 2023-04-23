package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    /**
     * Создание таблицы
     */
    public void createUsersTable() {
        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement()) {
            String sqlQueryCreateUsersTable = """
                    CREATE TABLE IF NOT EXISTS users
                    (
                        IdBD INT AUTO_INCREMENT PRIMARY KEY,
                        nameBD VARCHAR(45) NOT NULL,
                        lastNameBD VARCHAR(64) NOT NULL,
                        ageBD INT DEFAULT NULL
                      ); \s""";
            statement.executeUpdate(sqlQueryCreateUsersTable);
            System.out.println("Создана таблица \"users\"");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Произошла ошибка при создании таблицы..");
        }
    }

    /**
     * Удаление таблицы
     */
    public void dropUsersTable() {
        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement()) {
            String sqlQueryDropUsersTable = "drop table if exists users";
            statement.executeUpdate(sqlQueryDropUsersTable);
            System.out.println("Удалена таблица \"users\"");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Произошла ошибка при удалении таблицы..");
        }
    }

    /**
     * Сохранение нового юзера
     */
    public void saveUser(String name, String lastName, byte age) {
        String sqlQuerySaveUser = "insert into users (nameBD, lastNameBD, ageBD) values (?, ?, ?)";
        try (Connection connect = Util.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(sqlQuerySaveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            if (preparedStatement.executeUpdate() == 1) {
                System.out.println("Добавлен пользователь...Имя: " + name + ", Фамилия:" + lastName + ", возраст: " + age);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка добавления нового пользователя");
            e.printStackTrace();
        }
    }

    /**
     * Удаление человека по ИД
     */
    public void removeUserById(long id) {
        String sqlQueryRemoveUserById = "delete from users where idBD =?";
        try (Connection connect = Util.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(sqlQueryRemoveUserById)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь удален!");
        } catch (SQLException e) {
            System.err.println("Произошла ошибка удаления пользователя");
            e.printStackTrace();
        }
    }

    /**
     * Показать всех юзеров
     */
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement()) {
            System.out.println(" ");
            String sqlQueryGetAllUsers = "select * from users";
            ResultSet result = statement.executeQuery(sqlQueryGetAllUsers);
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("idBD"));
                user.setName(result.getString("nameBD"));
                user.setLastName(result.getString("lastNameBD"));
                user.setAge(result.getByte("ageBD"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Произошла ошибка...");
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement()) {
            String sqlQueryCleanUsersTable = "delete from users";
            statement.executeUpdate(sqlQueryCleanUsersTable);
            System.out.println("Удаление данных прошло успешно!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка удаления данных из таблицы");
        }
    }
}