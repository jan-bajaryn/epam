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


    /**
     * @return List of all {@link User} in base
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<User> findAll() throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            return userMysqlDao.findAll(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param part number of part of all entities {@link User} from the database,
     *             where maximum number of entities in one part is
     *             {@value by.epam.cafe.config.Configuration#MAX_PAGINATION_ELEMENTS}
     * @return List of {@link User} from the database related to part from input
     * or empty list if there no so part in database
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<User> findAllByPart(int part) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            return userMysqlDao.findAllByPart(transaction, (part - 1) * MAX_PAGINATION_ELEMENTS, MAX_PAGINATION_ELEMENTS);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param id identifier of {@link User}
     * @return entity from database identified by id, or {@code null} if
     * there no entity with so id
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    @Override
    public User findEntityById(Integer id) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            return userMysqlDao.findEntityById(id, transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    /**
     * @param id identifier of {@link User}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public boolean deleteById(Integer id) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = userMysqlDao.deleteById(id, transaction);
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

    /**
     * @param entity what dedicated to delete {@link User}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
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

    /**
     * @param entity {@link User} dedicated to create
     * @return created entity with new id, or {@code null} if entity can't be created
     * @throws ServiceException if service can't connect to the database
     */
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

    /**
     * @param entity {@link User} dedicated to update identified by id
     *               {@link by.epam.cafe.entity.db.Entity}
     * @return true if entity successfully updated otherwise returns false
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public boolean update(User entity) throws ServiceException {

//        if (!takeOldIfPasswordNull(entity)) {
//            return false;
//        }

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

    private boolean takeOldIfPasswordNull(User entity) throws ServiceException {
        if (entity == null || entity.getId() == null) {
            return false;
        }
        if (entity.getPassword() == null) {
            User entityById = findEntityById(entity.getId());
            if (entityById != null) {
                entity.setPassword(entityById.getPassword());
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * @param username from {@link User} what method should find
     * @return {@link User} from database with {@link User#getUsername()}
     * equals username from input, or {@code null} if there not so User
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public User findUserByUsername(String username) throws ServiceException {
        return findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny()
                .orElse(null);

    }

    /**
     * Block user identified by id
     * Set field blocked by {@link User#setBlocked(boolean)} to true
     * and than commit to the database
     *
     * @param id identifier of {@link User}
     * @throws IllegalIdException if id less than  or equals 0 or id is null
     *                            or there no so user identified by id in database
     * @throws ServiceException   if service can't connect to the database
     */
    @Override
    public void blockById(Integer id) throws ServiceException {
        if (id == null || id <= 0) {
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

    /**
     * Block user identified by id
     * Set field blocked by {@link User#setBlocked(boolean)} to false
     * and than commit to the database
     *
     * @param id identifier of {@link User}
     * @throws IllegalIdException if id less than  or equals 0 or id is null
     *                            or there no so user identified by id in database
     * @throws ServiceException   if service can't connect to the database
     */
    @Override
    public void unBlockById(Integer id) throws ServiceException {
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

    /**
     * @return count of {@link User} in the database
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public int count() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return userMysqlDao.count(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
