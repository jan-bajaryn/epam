package by.epam.cafe.service;

import by.epam.cafe.entity.impl.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findEntityById(Integer integer);

    boolean deleteById(Integer integer);

    boolean delete(User entity);

    boolean create(User entity);

    boolean update(User entity);

    User findUserByUsername(String username);
}
