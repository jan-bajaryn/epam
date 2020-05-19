package by.epam.cafe.service.db.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.exception.DaoException;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.dao.mysql.impl.DeliveryInfMysqlDao;
import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.db.DeliveryInfService;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

/**
 * implementation of interface {@link DeliveryInfService}
 */
public class DeliveryInfServiceImpl implements DeliveryInfService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();

    private DeliveryInfMysqlDao deliveryInfMysqlDao = dAOFactory.getDeliveryInfMysqlDao();


    /**
     * @return List of all {@link DeliveryInf} in base
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<DeliveryInf> findAll() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return deliveryInfMysqlDao.findAll(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    /**
     * @param id identifier of {@link DeliveryInf}
     * @return entity from database identified by id, or {@code null} if
     * there no entity with so id
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    @Override
    public DeliveryInf findEntityById(Integer id) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return deliveryInfMysqlDao.findEntityById(id, transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param id identifier of {@link DeliveryInf}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public boolean deleteById(Integer id) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = deliveryInfMysqlDao.deleteById(id, transaction);
            if (!result) {
                transaction.rollBack();
            } else {
                transaction.commit();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    /**
     * @param entity what dedicated to delete {@link DeliveryInf}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
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
            throw new ServiceException(e);
        }
    }


    /**
     * @param entity {@link DeliveryInf} dedicated to create
     * @return created entity with new id, or {@code null} if entity can't be created
     * @throws ServiceException if service can't connect to the database
     */
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
            throw new ServiceException(e);
        }
    }


    /**
     * @param entity {@link DeliveryInf} dedicated to update identified by id
     *               {@link by.epam.cafe.entity.db.Entity}
     * @return true if entity successfully updated otherwise returns false
     * @throws ServiceException if service can't connect to the database
     */
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
            throw new ServiceException(e);
        }
    }
}
