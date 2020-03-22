package by.epam.cafe.service;

import by.epam.cafe.entity.impl.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order findEntityById(Integer integer);

    boolean deleteById(Integer integer);

    boolean delete(Order entity);

    boolean create(Order entity);

    boolean update(Order entity);
}
