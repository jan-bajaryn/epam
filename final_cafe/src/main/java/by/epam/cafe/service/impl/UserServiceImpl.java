package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.UserMysqlDao;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.exception.IllegalIdException;

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

    @Override
    public void blockById(Integer id) throws IllegalIdException {
        if (id == null || id < 0) {
            throw new IllegalIdException("id = " + id);
        }
        User entityById = userMysqlDao.findEntityById(id);
        if (entityById == null) {
            throw new IllegalIdException("id = " + id);
        }

        if (!entityById.isBlocked()) {
            entityById.setBlocked(true);
            boolean update = userMysqlDao.update(entityById);

            if (!update) {
                throw new IllegalIdException("id = " + id);
            }
        }
    }

    @Override
    public void unBlockById(Integer id) throws IllegalIdException {
        if (id == null || id < 0) {
            throw new IllegalIdException("id = " + id);
        }
        User entityById = userMysqlDao.findEntityById(id);
        if (entityById == null) {
            throw new IllegalIdException("id = " + id);
        }

        if (entityById.isBlocked()) {
            entityById.setBlocked(false);
            boolean update = userMysqlDao.update(entityById);

            if (!update) {
                throw new IllegalIdException("id = " + id);
            }
        }

    }
}
