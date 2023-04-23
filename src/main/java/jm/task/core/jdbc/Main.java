package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService uService = new UserServiceImpl();

        uService.createUsersTable();
        uService.saveUser("Егор", "Цветков", (byte) 20);
        uService.saveUser("Екатерина", "Голда", (byte) 22);
        uService.saveUser("Никита", "Перевязкин", (byte) 24);
        uService.saveUser("Родион", "Копаев", (byte) 26);

        uService.removeUserById(1);

        List<User> Table = uService.getAllUsers();
        for (User user: Table){
            System.out.println(user.toString());
        }

        uService.cleanUsersTable();
        uService.dropUsersTable();
    }
}

