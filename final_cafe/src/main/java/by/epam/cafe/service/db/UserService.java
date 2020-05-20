package by.epam.cafe.service.db;

import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.exception.IllegalIdException;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface to working with {@link User}
 * Gives abilities for CRUD operations with {@link User}
 */
public interface UserService {

    /**
     * @return List of all {@link User} in base
     * @throws ServiceException if service can't connect to the database
     */
    List<User> findAll() throws ServiceException;

    /**
     * @param part number of part of all entities {@link User} from the database,
     *             where maximum number of entities in one part is
     *             {@value by.epam.cafe.config.Configuration#MAX_PAGINATION_ELEMENTS}
     * @return List of {@link User} from the database related to part from input
     * or empty list if there no so part in database
     * @throws ServiceException if service can't connect to the database
     */
    List<User> findAllByPart(int part) throws ServiceException;

    /**
     * @param id identifier of {@link User}
     * @return entity from database identified by id, or {@code null} if
     * there no entity with so id
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    User findEntityById(Integer id) throws ServiceException;

    /**
     * @param id identifier of {@link User}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
    boolean deleteById(Integer id) throws ServiceException;

    /**
     * @param entity what dedicated to delete {@link User}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
    boolean delete(User entity) throws ServiceException;

    /**
     * @param entity {@link User} dedicated to create
     * @return created entity with new id, or {@code null} if entity can't be created
     * @throws ServiceException if service can't connect to the database
     */
    User create(User entity) throws ServiceException;

    /**
     * @param entity {@link User} dedicated to update identified by id
     *               {@link by.epam.cafe.entity.db.Entity}
     * @return true if entity successfully updated otherwise returns false
     * @throws ServiceException if service can't connect to the database
     */
    boolean update(User entity) throws ServiceException;

    /**
     * @param username from {@link User} what method should find
     * @return {@link User} from database with {@link User#getUsername()}
     * equals username from input, or {@code null} if there not so User
     * @throws ServiceException if service can't connect to the database
     */
    User findUserByUsername(String username) throws ServiceException;

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
    void blockById(Integer id) throws ServiceException;

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
    void unBlockById(Integer id) throws ServiceException;

    /**
     * @return count of {@link User} in the database
     * @throws ServiceException if service can't connect to the database
     */
    int count() throws ServiceException;

}
