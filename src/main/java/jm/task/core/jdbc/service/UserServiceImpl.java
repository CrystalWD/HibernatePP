package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDAOmethods = new UserDaoJDBCImpl();


    public void createUsersTable() throws SQLException {
        userDAOmethods.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userDAOmethods.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDAOmethods.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        userDAOmethods.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAOmethods.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        userDAOmethods.cleanUsersTable();
    }
}
