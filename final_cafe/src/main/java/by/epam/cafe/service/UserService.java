package by.epam.cafe.service;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.UserMysqlDao;
import by.epam.cafe.entity.impl.User;

import java.util.List;

public class UserService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final UserMysqlDao userMysqlDao = dAOFactory.getUserMysqlDao();

    public List<User> findAll() {
        return userMysqlDao.findAll();
    }

    public User findEntityById(Integer integer) {
        return userMysqlDao.findEntityById(integer);
    }

    public boolean deleteById(Integer integer) {
        return userMysqlDao.deleteById(integer);
    }

    public boolean delete(User entity) {
        return userMysqlDao.delete(entity);
    }

    public boolean create(User entity) {
        return userMysqlDao.create(entity);
    }

    public boolean update(User entity) {
        return userMysqlDao.update(entity);
    }
}
