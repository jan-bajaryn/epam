package by.epam.cafe.service.factory;

import by.epam.cafe.service.helper.ImageWriterService;
import by.epam.cafe.service.helper.impl.ImageWriterServiceImpl;
import by.epam.cafe.service.helper.PutItemService;
import by.epam.cafe.service.helper.impl.PutItemServiceImpl;
import by.epam.cafe.service.db.*;
import by.epam.cafe.service.db.impl.*;
import by.epam.cafe.service.pagination.PaginationService;
import by.epam.cafe.service.pagination.impl.PaginationServiceImpl;
import by.epam.cafe.service.parser.full.OrderParser;
import by.epam.cafe.service.parser.full.ProductGroupParser;
import by.epam.cafe.service.parser.full.ProductParser;
import by.epam.cafe.service.parser.full.UserParser;
import by.epam.cafe.service.parser.helper.NullIfEmptyService;
import by.epam.cafe.service.pagination.PaginationCalculator;
import by.epam.cafe.service.pagination.impl.PaginationCalculatorImpl;
import by.epam.cafe.service.parser.helper.PathVarCalculator;
import by.epam.cafe.service.parser.full.impl.OrderParserImpl;
import by.epam.cafe.service.parser.full.impl.ProductGroupParserImpl;
import by.epam.cafe.service.parser.full.impl.ProductParserImpl;
import by.epam.cafe.service.parser.full.impl.UserParserImpl;
import by.epam.cafe.service.parser.helper.impl.NullIfEmptyServiceImpl;
import by.epam.cafe.service.parser.helper.impl.PathVarCalculatorImpl;
import by.epam.cafe.service.validator.parts.LoginValidator;

/**
 * Factory to give services for other layers
 */
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
    private final ProductService productServiceImpl = new ProductServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final PathVarCalculator pathVarCalculator = new PathVarCalculatorImpl();
    private final LoginValidator loginValidator = new LoginValidator();
    private final NullIfEmptyService nullIfEmptyService = new NullIfEmptyServiceImpl();
    private final PutItemService putItemService = new PutItemServiceImpl();

    private final UserParser userParser = new UserParserImpl();
    private final ProductParser productParser = new ProductParserImpl();
    private final ProductGroupParser productGroupParser = new ProductGroupParserImpl();
    private final OrderParser orderParser = new OrderParserImpl();

    private final PaginationService paginationService = new PaginationServiceImpl();

    private final PaginationCalculator paginationCalculator = new PaginationCalculatorImpl();

    private final ImageWriterService imageWriterService = new ImageWriterServiceImpl();

    public DeliveryInfService getDeliveryInfService() {
        return deliveryInfService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ProductGroupService getProductGroupService() {
        return productGroupService;
    }

    public ProductService getProductService() {
        return productServiceImpl;
    }

    public UserService getUserService() {
        return userService;
    }

    public PathVarCalculator getPathVarCalculator() {
        return pathVarCalculator;
    }

    public LoginValidator getLoginValidator() {
        return loginValidator;
    }

    public NullIfEmptyService getNullIfEmptyService() {
        return nullIfEmptyService;
    }

    public ImageWriterService getImageWriterServiceImpl() {
        return imageWriterService;
    }

    public PutItemService getPutItemService() {
        return putItemService;
    }

    public UserParser getUserParserImpl() {
        return userParser;
    }

    public ProductParser getProductParserImpl() {
        return productParser;
    }

    public ProductGroupParser getProductGroupParserImpl() {
        return productGroupParser;
    }

    public OrderParser getOrderParserImpl() {
        return orderParser;
    }

    public PaginationCalculator getPaginationCalculator() {
        return paginationCalculator;
    }

    public PaginationService getPaginationService() {
        return paginationService;
    }
}
