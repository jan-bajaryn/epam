package by.epam.cafe.dao;

import by.epam.cafe.dao.mysql.impl.*;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final DeliveryInfMysqlDao deliveryInfMysqlDao = new DeliveryInfMysqlDao(null);
    private final OrderMysqlDao orderMysqlDao = new OrderMysqlDao(null);
    private final ProductMysqlDao productMysqlDao = new ProductMysqlDao(null);
    private final ProductGroupMysqlDao productGroupMysqlDao = new ProductGroupMysqlDao(null);
    private final UserMysqlDao userMysqlDao = new UserMysqlDao(null);

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public DeliveryInfMysqlDao getDeliveryInfMysqlDao() {
        return deliveryInfMysqlDao;
    }

    public OrderMysqlDao getOrderMysqlDao() {
        return orderMysqlDao;
    }

    public ProductMysqlDao getProductMysqlDao() {
        return productMysqlDao;
    }

    public ProductGroupMysqlDao getProductGroupMysqlDao() {
        return productGroupMysqlDao;
    }

    public UserMysqlDao getUserMysqlDao() {
        return userMysqlDao;
    }
}
