package by.epam.cafe.service;

import by.epam.cafe.dao.AbstractDao;
import by.epam.cafe.dao.impl.OrderDao;
import by.epam.cafe.entity.impl.Order;

import java.sql.Connection;
import java.util.List;

public class OrderService {

    private OrderDao orderDao;

    public OrderService(Connection cn) {
        orderDao = new OrderDao(cn);
    }


    public List<Order> findAll() {
        return orderDao.findAll();
    }


    public Order findEntityById(Long id) {
        return orderDao.findEntityById(id);
    }


    public boolean delete(Long id) {
        return orderDao.delete(id);
    }


    public boolean delete(Order entity) {
        return orderDao.delete(entity);
    }


    public boolean create(Order entity) {
        return orderDao.create(entity);
    }


    public boolean update(Order entity) {
        return orderDao.update(entity);
    }


}
