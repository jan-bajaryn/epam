package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.DeliveryInfMysqlDao;
import by.epam.cafe.dao.mysql.impl.OrderMysqlDao;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.impl.DeliveryInf;
import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.service.ProductService;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements by.epam.cafe.service.OrderService {

    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderMysqlDao orderMysqlDao = daoFactory.getOrderMysqlDao();
    private final ProductMysqlDao productMysqlDao = daoFactory.getProductMysqlDao();
    private final DeliveryInfMysqlDao deliveryInfMysqlDao = daoFactory.getDeliveryInfMysqlDao();

    private final ProductService productService = new ProductServiceImpl();

    @Override
    public List<Order> findAll() {
        return orderMysqlDao.findAll().stream()
                .peek(this::buildOrder)
                .collect(Collectors.toList());
    }

    private void buildOrder(Order o) {
        log.debug("buildOrder: o = {}", o);
        Map<Product, Integer> products =
                productMysqlDao.findAllByOrderId(o.getId());
        Map<Product, Integer> buildingProducts = products.entrySet().stream()
                .map(p -> {
                    productService.buildProduct(p.getKey());
                    return Map.entry(p.getKey(), p.getValue());
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        log.debug("buildingProducts = {}", buildingProducts);
        o.setProducts(buildingProducts);
        log.debug("buildOrder: o = {}", o);

//                o.getProducts().stream()
//                .map(p -> productMysqlDao.findEntityById(p.getId()))
//                .collect(Collectors.toList());
        if (o.getDeliveryInf() != null) {

            DeliveryInf delInf = deliveryInfMysqlDao
                    .findEntityById(o.getDeliveryInf().getId());

            o.setDeliveryInf(delInf);
        }
        // TODO find product group and build
    }

    @Override
    public Order findEntityById(Integer integer) {
        Order order = orderMysqlDao.findEntityById(integer);
        if (order != null) {
            buildOrder(order);
        }
        return order;
    }

    @Override
    public boolean deleteById(Integer integer) {
        return orderMysqlDao.deleteById(integer);
    }

    @Override
    public boolean delete(Order entity) {
        return orderMysqlDao.delete(entity);
    }

    @Override
    public Order create(Order entity) {
        DeliveryInf deliveryInf = deliveryInfMysqlDao.create(entity.getDeliveryInf());
        entity.setDeliveryInf(deliveryInf);

        Order order = orderMysqlDao.create(entity);
        Map<Product, Integer> products = order.getProducts();
        saveProducts(products, order);
        return order;
    }

    private void saveProducts(Map<Product, Integer> products, Order order) {
        orderMysqlDao.addProductsOnCreate(products, order);
    }

    @Override
    public boolean update(Order entity) {
        boolean update = orderMysqlDao.update(entity);

        deliveryInfMysqlDao.update(entity.getDeliveryInf());
        return update;
    }
}
