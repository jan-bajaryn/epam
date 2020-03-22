package by.epam.cafe.service.factory;

import by.epam.cafe.service.DeliveryInfService;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.ProductGroupService;
import by.epam.cafe.service.UserService;
import by.epam.cafe.service.impl.*;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {
    }

    private final DeliveryInfService deliveryInfService = new DeliveryInfServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final ProductGroupService productGroupService = new ProductGroupServiceImpl();
    private final ProductServiceImpl productServiceImpl = new ProductServiceImpl();
    private final UserService userService = new UserServiceImpl();

    public DeliveryInfService getDeliveryInfService() {
        return deliveryInfService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ProductGroupService getProductGroupService() {
        return productGroupService;
    }

    public ProductServiceImpl getProductServiceImpl() {
        return productServiceImpl;
    }

    public UserService getUserService() {
        return userService;
    }
}
