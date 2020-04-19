package by.epam.cafe.service;

import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.exception.IllegalIdException;

import java.util.List;

/**
 * Dedicated to service necessary CRUD operations for class {@link User}
 */
public interface UserService {
    List<User> findAll();

    User findEntityById(Integer integer);

    boolean deleteById(Integer integer);

    boolean delete(User entity);

    User create(User entity);

    boolean update(User entity);

    User findUserByUsername(String username);

    void blockById(Integer id) throws IllegalIdException;

    public void unBlockById(Integer id) throws IllegalIdException;
}
