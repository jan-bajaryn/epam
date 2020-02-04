package by.epam.task09.example4.account.dao;

import by.epam.task09.example4.account.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    private static UserRepo singleton = new UserRepo();

    public static UserRepo getInstance() {
        return singleton;
    }

    private UserRepo() {
    }


    private List<User> users = new ArrayList<>();

    public User getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst().orElse(null);
    }

    public void save(User user) {
        User usr = users.stream()
                .filter(u -> user.getId() == u.getId())
                .findAny()
                .orElse(null);
        if (usr == null) {
            users.add(user);
        } else {
            usr.setName(user.getName());
            usr.setSurname(user.getSurname());
        }


    }

    public int lastId() {
        return users.stream()
                .map(user -> user.getId())
                .max(Integer::compareTo)
                .orElse(null);
    }

    public void clear() {
        users.clear();
    }
}
