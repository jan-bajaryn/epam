package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.exception.DaoException;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.dao.mysql.impl.DeliveryInfMysqlDao;
import by.epam.cafe.entity.impl.DeliveryInf;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

public class DeliveryInfServiceImpl implements by.epam.cafe.service.DeliveryInfService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();

    private DeliveryInfMysqlDao deliveryInfMysqlDao = dAOFactory.getDeliveryInfMysqlDao();


    @Override
    public List<DeliveryInf> findAll() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return deliveryInfMysqlDao.findAll(transaction);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }


    @Override
    public DeliveryInf findEntityById(Integer integer) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return deliveryInfMysqlDao.findEntityById(integer, transaction);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }


    @Override
    public boolean deleteById(Integer integer) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = deliveryInfMysqlDao.deleteById(integer, transaction);
            if (!result) {
                transaction.rollBack();
            } else {
                transaction.commit();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }


    @Override
    public boolean delete(DeliveryInf entity) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = deliveryInfMysqlDao.delete(entity, transaction);
            if (!result) {
                transaction.rollBack();
            } else {
                transaction.commit();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }


    @Override
    public DeliveryInf create(DeliveryInf entity) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            DeliveryInf deliveryInf = deliveryInfMysqlDao.create(entity, transaction);
            if (deliveryInf == null) {
                transaction.rollBack();
            } else {
                transaction.commit();
            }
            return deliveryInf;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }


    @Override
    public boolean update(DeliveryInf entity) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = deliveryInfMysqlDao.update(entity, transaction);
            if (!result) {
                transaction.rollBack();
            } else {
                transaction.commit();
            }
            return result;

        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
