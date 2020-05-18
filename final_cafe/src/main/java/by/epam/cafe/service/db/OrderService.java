package by.epam.cafe.service.db;

import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> findAll() throws ServiceException;

    List<Order> findAllByPart(int part) throws ServiceException;

    Order findEntityById(Integer integer) throws ServiceException;

    boolean delete(Order entity) throws ServiceException;

    Order create(Order entity) throws ServiceException;

    boolean update(Order entity) throws ServiceException;

    void plusProduct(Integer orderId, Integer prodId) throws ServiceException;

    void deleteProduct(Integer orderId, Integer prodId) throws ServiceException;

    void minusOrDelete(Integer orderId, Integer prodId) throws ServiceException;

    Order findOrCreateCurrentByUserId(Integer id) throws ServiceException;

    Order findCurrentByUserId(Integer id) throws ServiceException;

    boolean cancelOrDeleteById(Integer idInt) throws ServiceException;

    int count() throws ServiceException;
}
