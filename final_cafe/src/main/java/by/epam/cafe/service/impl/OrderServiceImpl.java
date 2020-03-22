package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.OrderMysqlDao;
import by.epam.cafe.entity.impl.Order;

import java.util.List;

public class OrderServiceImpl implements by.epam.cafe.service.OrderService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderMysqlDao orderMysqlDao = daoFactory.getOrderMysqlDao();

    @Override
    public List<Order> findAll() {
        return orderMysqlDao.findAll();
    }

    @Override
    public Order findEntityById(Integer integer) {
        return orderMysqlDao.findEntityById(integer);
    }

    @Override
    public boolean deleteById(Integer integer) {
        return orderMysqlDao.deleteById(integer);
    }

    @Override
    public boolean delete(Order entity) {
        return orderMysqlDao.delete(entity);
    }

    @Override
    public boolean create(Order entity) {
        return orderMysqlDao.create(entity);
    }

    @Override
    public boolean update(Order entity) {
        return orderMysqlDao.update(entity);
    }
}
