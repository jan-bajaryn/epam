package by.epam.cafe.service.db;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

public interface DeliveryInfService {
    List<DeliveryInf> findAll() throws ServiceException;

    DeliveryInf findEntityById(Integer integer) throws ServiceException;

    boolean deleteById(Integer integer) throws ServiceException;

    boolean delete(DeliveryInf entity) throws ServiceException;

    DeliveryInf create(DeliveryInf entity) throws ServiceException;

    boolean update(DeliveryInf entity) throws ServiceException;
}
