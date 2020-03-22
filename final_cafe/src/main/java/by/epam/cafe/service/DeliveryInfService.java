package by.epam.cafe.service;

import by.epam.cafe.entity.impl.DeliveryInf;

import java.util.List;

public interface DeliveryInfService {
    List<DeliveryInf> findAll();

    DeliveryInf findEntityById(Integer integer);

    boolean deleteById(Integer integer);

    boolean delete(DeliveryInf entity);

    boolean create(DeliveryInf entity);

    boolean update(DeliveryInf entity);
}
