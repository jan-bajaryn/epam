package by.epam.cafe.dao;

import by.epam.cafe.dao.my_sql.impl.*;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final DeliveryInfMysqlDao deliveryInfMysqlDao = new DeliveryInfMysqlDao();
    private final OrderMysqlDao orderMysqlDao = new OrderMysqlDao();
    private final ProductMysqlDao productMysqlDao = new ProductMysqlDao();
    private final ProductGroupMysqlDao productGroupMysqlDao = new ProductGroupMysqlDao();
    private final UserMysqlDao userMysqlDao = new UserMysqlDao();

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
