package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDAOmethods = new UserDaoJDBCImpl();


    public void createUsersTable(){
        userDAOmethods.createUsersTable();
    }

    public void dropUsersTable(){
        userDAOmethods.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age){
        userDAOmethods.saveUser(name, lastName, age);
    }

    public void removeUserById(long id){
        userDAOmethods.removeUserById(id);
    }

    public List<User> getAllUsers(){
        return userDAOmethods.getAllUsers();
    }

    public void cleanUsersTable(){
        userDAOmethods.cleanUsersTable();
    }
}
