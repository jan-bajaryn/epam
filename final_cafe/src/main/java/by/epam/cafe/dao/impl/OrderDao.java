package by.epam.cafe.dao.impl;

import by.epam.cafe.dao.AbstractDao;
import by.epam.cafe.entity.impl.Order;

import java.sql.Connection;
import java.util.List;

public class OrderDao implements AbstractDao<Integer, Order> {

    private Connection cn;

    public OrderDao(Connection cn) {
        this.cn = cn;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Order entity) {
        return false;
    }

    @Override
    public boolean create(Order entity) {
        return false;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }
}
