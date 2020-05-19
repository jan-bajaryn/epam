package by.epam.cafe.service.db;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface to working with {@link Order}
 * Gives abilities for CRUD operations with {@link Order}
 */
public interface OrderService {

    /**
     * @return List of all {@link Order} in base
     * @throws ServiceException if service can't connect to the database
     */
    List<Order> findAll() throws ServiceException;


    /**
     * @param part number of part of all entities {@link Order} from the database,
     *             where maximum number of entities in one part is {@value by.epam.cafe.config.Configuration#MAX_PAGINATION_ELEMENTS}
     * @return List of {@link Order} from the database related to part from input
     * or empty list if there no so part in database
     * @throws ServiceException if service can't connect to the database
     */
    List<Order> findAllByPart(int part) throws ServiceException;

    /**
     * @param id identifier of {@link Order}
     * @return entity from database identified by id, or {@code null} if
     * there no entity with so id
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    Order findEntityById(Integer id) throws ServiceException;

    /**
     * @param entity what dedicated to delete {@link DeliveryInf}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
    boolean delete(Order entity) throws ServiceException;

    /**
     * @param entity {@link Order} dedicated to create
     * @return created entity with new id, or {@code null} if entity can't be created
     * @throws ServiceException if service can't connect to the database
     */
    Order create(Order entity) throws ServiceException;

    /**
     * @param entity {@link Order} dedicated to update identified by id
     *               {@link by.epam.cafe.entity.db.Entity}
     * @return true if entity successfully updated otherwise returns false
     * @throws ServiceException if service can't connect to the database
     */
    boolean update(Order entity) throws ServiceException;


    /**
     * Add one product to the order
     *
     * @param orderId identifier of {@link Order} in the database where
     *                should be added the Product
     * @param prodId  identifier of {@link by.epam.cafe.entity.db.impl.Product} in the
     *                database what should be added to the order
     * @throws ServiceException if service can't connect to the database
     *                          or if service can't add the product to the order (database restrictions,
     *                          there no so {@link Order} with orderId, there no so
     *                          {@link by.epam.cafe.entity.db.impl.Product}
     *                          with so productId)
     * @see by.epam.cafe.entity.db.Entity
     */
    void plusProduct(Integer orderId, Integer prodId) throws ServiceException;

    /**
     * Delete all products with so prodId from the order
     *
     * @param orderId identifier of {@link Order} in the database from where
     *                should be deleted the Product
     * @param prodId  identifier of {@link by.epam.cafe.entity.db.impl.Product} in the
     *                database what should be deleted from the order
     * @throws ServiceException if service can't connect to the database
     *                          or if service can't delete the product from the order (database restrictions,
     *                          there is no so {@link Order} with orderId
     *                          in the database, there is no so
     *                          {@link by.epam.cafe.entity.db.impl.Product}
     *                          with so productId in the database)
     * @see by.epam.cafe.entity.db.Entity
     */
    void deleteProduct(Integer orderId, Integer prodId) throws ServiceException;

    /**
     * Minus one product from the order or delete if there was only one  product with
     * so prodId in the order
     *
     * @param orderId identifier of {@link Order} in the database
     * @param prodId  identifier of {@link by.epam.cafe.entity.db.impl.Product} in the
     *                database
     * @throws ServiceException if service can't connect to the database
     *                          or if service can't make the operation (database restrictions,
     *                          there is no so {@link Order} with orderId
     *                          in the database, there is no so
     *                          {@link by.epam.cafe.entity.db.impl.Product}
     *                          with so productId in the database)
     * @see by.epam.cafe.entity.db.Entity
     */
    void minusOrDelete(Integer orderId, Integer prodId) throws ServiceException;


    /**
     * Returns existing or newly created {@link Order} from the database of
     * {@link by.epam.cafe.entity.db.impl.User}, where order has status
     * {@link by.epam.cafe.entity.enums.OrderStatus#WAITING}
     *
     * @param userId identifier of {@link by.epam.cafe.entity.db.impl.User} in the database
     * @return existing or newly created {@link Order}
     * with status {@link by.epam.cafe.entity.enums.OrderStatus#WAITING}
     * or code {@code null} if there no so user with so userId, or database
     * restrictions not allows this operation
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    Order findOrCreateCurrentByUserId(Integer userId) throws ServiceException;

    /**
     * Returns existing {@link Order} from the database of
     * {@link by.epam.cafe.entity.db.impl.User}, where order has status
     * {@link by.epam.cafe.entity.enums.OrderStatus#WAITING}
     *
     * @param id identifier of {@link by.epam.cafe.entity.db.impl.User} in the database
     * @return existing  {@link Order}
     * with status {@link by.epam.cafe.entity.enums.OrderStatus#WAITING}
     * or {@code null} if there no so order
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    Order findCurrentByUserId(Integer id) throws ServiceException;

    /**
     * Make status of order {@link by.epam.cafe.entity.enums.OrderStatus#CANCELED} if
     * it has status not {@link by.epam.cafe.entity.enums.OrderStatus#WAITING},
     * otherwise delete the order
     *
     * @param idInt identifier of {@link Order} in the database
     * @return true if operation successfully executed,
     * false if operation failed
     * @throws ServiceException if service can't connect to the database,
     *                          if idInt is {@code null} or there no so {@link Order}
     *                          in the database, or operation is denied by database
     *                          restrictions
     */
    boolean cancelOrDeleteById(Integer idInt) throws ServiceException;

    /**
     * @return count of {@link Order} in the database
     * @throws ServiceException if service can't connect to the database
     */
    int count() throws ServiceException;
}
