package by.epam.cafe.service;

import by.epam.cafe.dao.impl.UserDao;

import java.sql.Connection;
import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService(Connection cn) {
        userDao = new UserDao(cn);
    }


    public List<User> findAll() {
        return userDao.findAll();
    }


    public User findEntityById(Long id) {
        return userDao.findEntityById(id);
    }


    public boolean delete(Long id) {
        return userDao.delete(id);
    }


    public boolean delete(User entity) {
        return userDao.delete(entity);
    }


    public boolean create(User entity) {
        return userDao.create(entity);
    }


    public boolean update(User entity) {
        return userDao.update(entity);
    }
}
