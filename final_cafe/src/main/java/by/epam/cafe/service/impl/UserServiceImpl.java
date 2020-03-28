package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.UserMysqlDao;
import by.epam.cafe.entity.impl.User;

import java.util.List;

public class UserServiceImpl implements by.epam.cafe.service.UserService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final UserMysqlDao userMysqlDao = dAOFactory.getUserMysqlDao();

    @Override
    public List<User> findAll() {
        return userMysqlDao.findAll();
    }

    @Override
    public User findEntityById(Integer integer) {
        return userMysqlDao.findEntityById(integer);
    }

    @Override
    public boolean deleteById(Integer integer) {
        return userMysqlDao.deleteById(integer);
    }

    @Override
    public boolean delete(User entity) {
        return userMysqlDao.delete(entity);
    }

    @Override
    public boolean create(User entity) {
        return userMysqlDao.create(entity);
    }

    @Override
    public boolean update(User entity) {
        return userMysqlDao.update(entity);
    }


//    TODO complete this method with dao
    @Override
    public User findUserByUsername(String username) {
        return findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny()
                .orElse(null);

    }
}
