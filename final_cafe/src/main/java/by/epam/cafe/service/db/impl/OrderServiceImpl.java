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

    @Override
    public Order findEntityById(Integer integer) throws ServiceException {

        try (final Transaction transaction = dAOFactory.createTransaction()) {
            Order order = orderMysqlDao.findEntityById(integer, transaction);
            if (order != null) {
                buildOrder(order, transaction);
            }
            return order;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }


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

    @Override
    public int count() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return orderMysqlDao.count(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
