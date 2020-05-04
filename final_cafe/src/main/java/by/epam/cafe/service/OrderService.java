package by.epam.cafe.service;

import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order findEntityById(Integer integer);

    boolean deleteById(Integer integer);

    boolean delete(Order entity);

    Order create(Order entity);

    boolean update(Order entity);

    void plusProduct(Integer orderId, Integer prodId) throws ServiceException;

    void deleteProduct(Integer orderId, Integer prodId) throws ServiceException;

    void minusOrDelete(Integer orderId, Integer prodId) throws ServiceException;

    Order findOrCreateCurrentByUserId(Integer id);

    Order findCurrentByUserId(Integer id);

    boolean cancelOrDeleteById(Integer idInt);
}
