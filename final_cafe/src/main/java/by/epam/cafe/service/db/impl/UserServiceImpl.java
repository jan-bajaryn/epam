package by.epam.cafe.service.db.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.exception.DaoException;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.dao.mysql.impl.UserMysqlDao;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.db.UserService;
import by.epam.cafe.service.exception.IllegalIdException;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

import static by.epam.cafe.config.Configuration.MAX_PAGINATION_ELEMENTS;

public class UserServiceImpl implements UserService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final UserMysqlDao userMysqlDao = dAOFactory.getUserMysqlDao();

    @Override
    public List<User> findAll() throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            return userMysqlDao.findAll(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllByPart(int part) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            return userMysqlDao.findAllByPart(transaction, (part - 1) * MAX_PAGINATION_ELEMENTS, MAX_PAGINATION_ELEMENTS);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findEntityById(Integer integer) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            return userMysqlDao.findEntityById(integer, transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteById(Integer integer) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = userMysqlDao.deleteById(integer, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(User entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = userMysqlDao.delete(entity, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User create(User entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            User user = userMysqlDao.create(entity, transaction);
            if (user == null) {
                transaction.rollBack();
            } else {
                transaction.commit();
            }
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }


    }

    @Override
    public boolean update(User entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = userMysqlDao.update(entity, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    //    TODO complete this method with dao
    @Override
    public User findUserByUsername(String username) throws ServiceException {
        return findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny()
                .orElse(null);

    }

    @Override
    public void blockById(Integer id) throws IllegalIdException, ServiceException {
        if (id == null || id < 0) {
            throw new IllegalIdException("id = " + id);
        }
        try (Transaction transaction = dAOFactory.createTransaction()) {
            User entityById = userMysqlDao.findEntityById(id, transaction);
            if (entityById == null) {
                throw new IllegalIdException("id = " + id);
            }

            if (!entityById.isBlocked()) {
                entityById.setBlocked(true);
                boolean update = userMysqlDao.update(entityById, transaction);

                if (!update) {
                    transaction.rollBack();
                    throw new IllegalIdException("id = " + id);
                } else {
                    transaction.commit();
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void unBlockById(Integer id) throws IllegalIdException, ServiceException {
        if (id == null || id < 0) {
            throw new IllegalIdException("id = " + id);
        }
        try (Transaction transaction = dAOFactory.createTransaction()) {
            User entityById = userMysqlDao.findEntityById(id, transaction);
            if (entityById == null) {
                throw new IllegalIdException("id = " + id);
            }

            if (entityById.isBlocked()) {
                entityById.setBlocked(false);
                boolean update = userMysqlDao.update(entityById, transaction);

                if (!update) {
                    transaction.rollBack();
                    throw new IllegalIdException("id = " + id);
                } else {
                    transaction.commit();
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int count() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return userMysqlDao.count(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
