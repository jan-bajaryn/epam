package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.DeliveryInfMysqlDao;
import by.epam.cafe.entity.impl.DeliveryInf;

import java.util.List;

public class DeliveryInfServiceImpl implements by.epam.cafe.service.DeliveryInfService {

    DAOFactory daoFactory = DAOFactory.getInstance();

    private DeliveryInfMysqlDao deliveryInfMysqlDao = daoFactory.getDeliveryInfMysqlDao();


    @Override
    public List<DeliveryInf> findAll() {
        return deliveryInfMysqlDao.findAll();
    }


    @Override
    public DeliveryInf findEntityById(Integer integer) {
        return deliveryInfMysqlDao.findEntityById(integer);
    }


    @Override
    public boolean deleteById(Integer integer) {
        return deliveryInfMysqlDao.deleteById(integer);
    }


    @Override
    public boolean delete(DeliveryInf entity) {
        return deliveryInfMysqlDao.delete(entity);
    }


    @Override
    public boolean create(DeliveryInf entity) {
        return deliveryInfMysqlDao.create(entity);
    }


    @Override
    public boolean update(DeliveryInf entity) {
        return deliveryInfMysqlDao.update(entity);
    }
}
