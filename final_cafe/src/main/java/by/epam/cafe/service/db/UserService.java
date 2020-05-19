package by.epam.cafe.service.db;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.exception.IllegalIdException;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface to working with {@link User}
 * Gives abilities for CRUD operations with {@link User}
 */
public interface UserService {
    List<User> findAll() throws ServiceException;

    List<User> findAllByPart(int part) throws ServiceException;

    User findEntityById(Integer integer) throws ServiceException;

    boolean deleteById(Integer integer) throws ServiceException;

    boolean delete(User entity) throws ServiceException;

    User create(User entity) throws ServiceException;

    boolean update(User entity) throws ServiceException;

    User findUserByUsername(String username) throws ServiceException;

    void blockById(Integer id) throws IllegalIdException, ServiceException;

    void unBlockById(Integer id) throws IllegalIdException, ServiceException;

    int count() throws ServiceException;

}
