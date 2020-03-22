package by.epam.cafe.service;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.DeliveryInfMysqlDao;
import by.epam.cafe.entity.impl.DeliveryInf;

import java.util.List;

public class DeliveryInfService {

    DAOFactory daoFactory = DAOFactory.getInstance();

    private DeliveryInfMysqlDao deliveryInfMysqlDao = daoFactory.getDeliveryInfMysqlDao();


    public List<DeliveryInf> findAll() {
        return deliveryInfMysqlDao.findAll();
    }


    public DeliveryInf findEntityById(Integer integer) {
        return deliveryInfMysqlDao.findEntityById(integer);
    }


    public boolean deleteById(Integer integer) {
        return deliveryInfMysqlDao.deleteById(integer);
    }


    public boolean delete(DeliveryInf entity) {
        return deliveryInfMysqlDao.delete(entity);
    }


    public boolean create(DeliveryInf entity) {
        return deliveryInfMysqlDao.create(entity);
    }


    public boolean update(DeliveryInf entity) {
        return deliveryInfMysqlDao.update(entity);
    }
}
