package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.DeliveryInfMysqlDao;
import by.epam.cafe.dao.mysql.impl.OrderMysqlDao;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.impl.DeliveryInf;
import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.entity.impl.Product;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements by.epam.cafe.service.OrderService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderMysqlDao orderMysqlDao = daoFactory.getOrderMysqlDao();
    private final ProductMysqlDao productMysqlDao = daoFactory.getProductMysqlDao();
    private final DeliveryInfMysqlDao deliveryInfMysqlDao = daoFactory.getDeliveryInfMysqlDao();

    @Override
    public List<Order> findAll() {
        return orderMysqlDao.findAll().stream()
                .peek(this::buildOrder)
                .collect(Collectors.toList());
    }

    private void buildOrder(Order o) {
        List<Product> products = o.getProducts().stream()
                .map(p -> productMysqlDao.findEntityById(p.getId()))
                .collect(Collectors.toList());
        if (o.getDeliveryInf() != null) {

            DeliveryInf delInf = deliveryInfMysqlDao
                    .findEntityById(o.getDeliveryInf().getId());

            o.setDeliveryInf(delInf);
        }
        // TODO find product group and build
        o.setProducts(products);
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
    public boolean create(Order entity) {
        return orderMysqlDao.create(entity);
    }

    @Override
    public boolean update(Order entity) {
        return orderMysqlDao.update(entity);
    }
}
