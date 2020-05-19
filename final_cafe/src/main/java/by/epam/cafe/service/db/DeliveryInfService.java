package by.epam.cafe.service.db;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface to working with {@link DeliveryInf}
 * Gives abilities for CRUD operations with {@link DeliveryInf}
 */
public interface DeliveryInfService {

    /**
     * @return List of all {@link DeliveryInf} in base
     * @throws ServiceException if service can't connect to the database
     */
    List<DeliveryInf> findAll() throws ServiceException;

    /**
     * @param id identifier of {@link DeliveryInf}
     * @return entity from database identified by id, or {@code null} if
     * there no entity with so id
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    DeliveryInf findEntityById(Integer id) throws ServiceException;

    /**
     * @param id identifier of {@link DeliveryInf}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
    boolean deleteById(Integer id) throws ServiceException;

    /**
     * @param entity what dedicated to delete {@link DeliveryInf}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
    boolean delete(DeliveryInf entity) throws ServiceException;

    /**
     * @param entity {@link DeliveryInf} dedicated to create
     * @return created entity with new id, or {@code null} if entity can't be created
     * @throws ServiceException if service can't connect to the database
     */
    DeliveryInf create(DeliveryInf entity) throws ServiceException;

    /**
     * @param entity {@link DeliveryInf} dedicated to update identified by id
     *               {@link by.epam.cafe.entity.db.Entity}
     * @return true if entity successfully updated otherwise returns false
     * @throws ServiceException if service can't connect to the database
     */
    boolean update(DeliveryInf entity) throws ServiceException;
}
