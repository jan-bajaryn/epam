package by.epam.cafe.service;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.my_sql.impl.OrderMysqlDao;
import by.epam.cafe.entity.impl.Order;

import java.util.List;

public class OrderService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderMysqlDao orderMysqlDao = daoFactory.getOrderMysqlDao();

    public List<Order> findAll() {
        return orderMysqlDao.findAll();
    }

    public Order findEntityById(Integer integer) {
        return orderMysqlDao.findEntityById(integer);
    }

    public boolean deleteById(Integer integer) {
        return orderMysqlDao.deleteById(integer);
    }

    public boolean delete(Order entity) {
        return orderMysqlDao.delete(entity);
    }

    public boolean create(Order entity) {
        return orderMysqlDao.create(entity);
    }

    public boolean update(Order entity) {
        return orderMysqlDao.update(entity);
    }
}
