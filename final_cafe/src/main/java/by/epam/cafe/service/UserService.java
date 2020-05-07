package by.epam.cafe.service;

import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.exception.IllegalIdException;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

/**
 * Dedicated to service necessary CRUD operations for class {@link User}
 */
public interface UserService {
    List<User> findAll() throws ServiceException;

    List<User> findAllByPart(int begin, int count) throws ServiceException;

    User findEntityById(Integer integer) throws ServiceException;

    boolean deleteById(Integer integer) throws ServiceException;

    boolean delete(User entity) throws ServiceException;

    User create(User entity) throws ServiceException;

    boolean update(User entity) throws ServiceException;

    User findUserByUsername(String username) throws ServiceException;

    void blockById(Integer id) throws IllegalIdException, ServiceException;

    void unBlockById(Integer id) throws IllegalIdException, ServiceException;
}
