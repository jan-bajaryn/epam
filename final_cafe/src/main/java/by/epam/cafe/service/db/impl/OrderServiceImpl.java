package by.epam.cafe.service.db.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.exception.DaoException;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.dao.mysql.impl.DeliveryInfMysqlDao;
import by.epam.cafe.dao.mysql.impl.OrderMysqlDao;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.db.ProductService;
import by.epam.cafe.service.db.OrderService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static by.epam.cafe.config.Configuration.MAX_PAGINATION_ELEMENTS;

public class OrderServiceImpl implements OrderService {

    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final OrderMysqlDao orderMysqlDao = dAOFactory.getOrderMysqlDao();
    private final ProductMysqlDao productMysqlDao = dAOFactory.getProductMysqlDao();
    private final DeliveryInfMysqlDao deliveryInfMysqlDao = dAOFactory.getDeliveryInfMysqlDao();

    private final ProductService productService = new ProductServiceImpl();

    /**
     * @return List of all {@link Order} in base
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<Order> findAll() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return orderMysqlDao.findAll(transaction).stream()
                    .peek(o -> buildOrder(o, transaction))
                    .collect(Collectors.toList());

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param part number of part of all entities {@link Order} from the database,
     *             where maximum number of entities in one part is {@value by.epam.cafe.config.Configuration#MAX_PAGINATION_ELEMENTS}
     * @return List of {@link Order} from the database related to part from input
     * or empty list if there no so part in database
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<Order> findAllByPart(int part) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return orderMysqlDao.findAllByPart(transaction, (part - 1) * MAX_PAGINATION_ELEMENTS, MAX_PAGINATION_ELEMENTS).stream()
                    .peek(o -> buildOrder(o, transaction))
                    .collect(Collectors.toList());

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void buildOrder(Order o, Transaction transaction) {
        log.debug("buildOrder: o = {}", o);
        Map<Product, Integer> products =
                productMysqlDao.findAllByOrderId(o.getId(), transaction);
        Map<Product, Integer> buildingProducts = products.entrySet().stream()
                .map(p -> {
                    productService.buildProduct(p.getKey(), transaction);
                    return Map.entry(p.getKey(), p.getValue());
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        log.debug("buildingProducts = {}", buildingProducts);
        o.setProducts(buildingProducts);
        log.debug("buildOrder: o = {}", o);

        if (o.getDeliveryInf() != null) {

            DeliveryInf delInf = deliveryInfMysqlDao
                    .findEntityById(o.getDeliveryInf().getId(), transaction);

            o.setDeliveryInf(delInf);
        }
    }

    /**
     * @param id identifier of {@link Order}
     * @return entity from database identified by id, or {@code null} if
     * there no entity with so id
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    @Override
    public Order findEntityById(Integer id) throws ServiceException {

        try (final Transaction transaction = dAOFactory.createTransaction()) {
            Order order = orderMysqlDao.findEntityById(id, transaction);
            if (order != null) {
                buildOrder(order, transaction);
            }
            return order;
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
    public boolean delete(Order entity) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {

            boolean result = orderMysqlDao.delete(entity, transaction);
            log.debug("result = {}", result);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param entity {@link Order} dedicated to create
     * @return created entity with new id, or {@code null} if entity can't be created
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public Order create(Order entity) throws ServiceException {

        if (entity == null) {
            return null;
        }

        try (final Transaction transaction = dAOFactory.createTransaction()) {
            log.debug("create1");
            DeliveryInf deliveryInf = deliveryInfMysqlDao.create(entity.getDeliveryInf(), transaction);

            if (deliveryInf == null) {
                transaction.rollBack();
                return null;
            }

            entity.setDeliveryInf(deliveryInf);

            Order order = orderMysqlDao.create(entity, transaction);

            if (order == null) {
                transaction.rollBack();
                return null;
            }

            Map<Product, Integer> products = order.getProducts();
            boolean result = saveProducts(products, order, transaction);
            if (!result) {
                transaction.rollBack();
                return null;
            } else {
                transaction.commit();
            }
            return order;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    private boolean saveProducts(Map<Product, Integer> products, Order order, Transaction transaction) {
        return orderMysqlDao.addProductsOnCreate(products, order, transaction);
    }

    /**
     * @param entity {@link Order} dedicated to update identified by id
     *               {@link by.epam.cafe.entity.db.Entity}
     * @return true if entity successfully updated otherwise returns false
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public boolean update(Order entity) throws ServiceException {

        if (entity == null || entity.getId() == null) {
            return false;
        }

        try (final Transaction transaction = dAOFactory.createTransaction()) {

            Order oldOrder = orderMysqlDao.findEntityById(entity.getId(), transaction);
            DeliveryInf curDeliveryInf = entity.getDeliveryInf();

            setIdToDeliveryInf(oldOrder, curDeliveryInf);

            if (!saveDeliveryInf(transaction, curDeliveryInf, entity)) {
                return false;
            }
            boolean result = orderMysqlDao.update(entity, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    private boolean saveDeliveryInf(Transaction transaction, DeliveryInf curDeliveryInf, Order order) throws DaoException {
        if (curDeliveryInf != null && curDeliveryInf.getId() != null) {
            boolean update = deliveryInfMysqlDao.update(curDeliveryInf, transaction);
            if (!update) {
                transaction.rollBack();
                return false;
            }

        } else {
            DeliveryInf deliveryInf = deliveryInfMysqlDao.create(curDeliveryInf, transaction);
            if (deliveryInf == null) {
                transaction.rollBack();
                return false;
            }
        }
        return true;
    }

    private void setIdToDeliveryInf(Order oldOrder, DeliveryInf curDeliveryInf) {
        if (oldOrder != null &&
                oldOrder.getDeliveryInf() != null &&
                oldOrder.getDeliveryInf().getId() != null) {
            curDeliveryInf.setId(oldOrder.getDeliveryInf().getId());
        }
    }

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
    @Override
    public void plusProduct(final Integer orderId, final Integer prodId) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {

            if (!orderMysqlDao.plusProductFirst(orderId, prodId, transaction)) {
                log.debug("executing second");
                if (!orderMysqlDao.plusExistingProduct(orderId, prodId, transaction)) {
                    transaction.rollBack();
                    throw new ServiceException();
                } else {
                    transaction.commit();
                }
            } else {
                transaction.commit();
            }
            log.debug("executed first");

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

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
    @Override
    public void deleteProduct(Integer orderId, Integer prodId) throws ServiceException {

        try (final Transaction transaction = dAOFactory.createTransaction()) {

            boolean result = orderMysqlDao.removeProduct(orderId, prodId, transaction);
            log.debug("deleteProduct: result = {}", result);
            if (!result) {
                transaction.rollBack();
                throw new ServiceException();
            } else {
                transaction.commit();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

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
    @Override
    public void minusOrDelete(Integer orderId, Integer prodId) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            if (!orderMysqlDao.minusProduct(orderId, prodId, transaction)) {
                if (!orderMysqlDao.removeProduct(orderId, prodId, transaction)) {
                    transaction.rollBack();
                    throw new ServiceException();
                } else {
                    transaction.commit();
                }
            } else {
                transaction.commit();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

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
    @Override
    public Order findOrCreateCurrentByUserId(Integer userId) throws ServiceException {

        try (final Transaction transaction = dAOFactory.createTransaction()) {
            Order current = findCurrentByUserId(userId);
            if (current == null) {
                current = orderMysqlDao.create(
                        Order.newBuilder()
                                .user(User.newBuilder().id(userId).build())
                                .status(OrderStatus.WAITING)
                                .build(),
                        transaction
                );
            }
            log.debug("current = {}", current);
            buildOrder(current, transaction);
            if (current != null) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return current;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

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
    @Override
    public Order findCurrentByUserId(Integer id) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            Order current = orderMysqlDao.findCurrentByUserId(id, transaction);
            if (current != null) {
                buildOrder(current, transaction);
            }
            return current;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

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
    @Override
    public boolean cancelOrDeleteById(Integer idInt) throws ServiceException {
        log.debug("cancelOrDeleteById working...");

        if (idInt == null) {
            throw new ServiceException("Input is null");
        }

        Order entityById = findEntityById(idInt);
        log.debug("cancelOrDeleteById: entityById = {}", entityById);

        if (entityById == null) {
            throw new ServiceException("No order with so id. id = " + idInt);
        }

        if (entityById.getStatus() == OrderStatus.WAITING) {
            log.debug("deleting...");
            return delete(entityById);

        } else {
            entityById.setStatus(OrderStatus.CANCELED);
            return update(entityById);
        }
    }

    /**
     * @return count of {@link Order} in the database
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public int count() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return orderMysqlDao.count(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
