package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args){
       UserService uService = new UserServiceImpl();

        //uService.createUsersTable();
        //uService.saveUser("Егор", "Цветков", (byte) 20);
        //uService.saveUser("Екатерина", "Голда", (byte) 22);
        //uService.saveUser("Никита", "Перевязкин", (byte) 24);
        //uService.saveUser("Родион", "Копаев", (byte) 26);

        //uService.removeUserById(1);

        //List<User> table = uService.getAllUsers();
        //for (User user: table){
          //  System.out.println(user.toString());
        //}

        //uService.cleanUsersTable();
        //uService.dropUsersTable();

    }
}

