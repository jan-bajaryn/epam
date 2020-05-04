package by.epam.cafe.service.factory;

import by.epam.cafe.service.*;
import by.epam.cafe.service.impl.*;
import by.epam.cafe.service.parser.NullIfEmptyService;
import by.epam.cafe.service.parser.PathVarCalculator;
import by.epam.cafe.service.parser.impl.NullIfEmptyServiceImpl;
import by.epam.cafe.service.parser.impl.PathVarCalculatorImpl;
import by.epam.cafe.service.parser.parts.impl.*;
import by.epam.cafe.service.validator.ProductGroupValidator;
import by.epam.cafe.service.validator.ProductValidator;
import by.epam.cafe.service.validator.parts.LoginValidator;
import by.epam.cafe.service.validator.UserValidator;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {
    }

    private final ProductValidator productValidator = new ProductValidator();
    private final DeliveryInfService deliveryInfService = new DeliveryInfServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final ProductGroupService productGroupService = new ProductGroupServiceImpl();
    private final ProductService productServiceImpl = new ProductServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final PathVarCalculator pathVarCalculator = new PathVarCalculatorImpl();
    private final LoginValidator loginValidator = new LoginValidator();
    private final UserValidator userValidator = new UserValidator();
    private final NullIfEmptyService nullIfEmptyService = new NullIfEmptyServiceImpl();
    private final ProductGroupValidator productGroupValidator = new ProductGroupValidator();
    private final PutItemService putItemService = new PutItemServiceImpl();

    private final EmailParser emailParser = new EmailParser();
    private final FloorParser floorParser = new FloorParser();
    private final HouseParser houseParser = new HouseParser();
    private final NameParser nameParser = new NameParser();
    private final PasswordParser passwordParser = new PasswordParser();
    private final PhoneParser phoneParser = new PhoneParser();
    private final PorchParser porchParser = new PorchParser();
    private final RoleParser roleParser = new RoleParser();
    private final RoomParser roomParser = new RoomParser();
    private final StreetParser streetParser = new StreetParser();
    private final UsernameParser usernameParser = new UsernameParser();
    private final SurnameParser surnameParser = new SurnameParser();


    private final ImageWriterService imageWriterService = new ImageWriterService();

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

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public NullIfEmptyService getNullIfEmptyService() {
        return nullIfEmptyService;
    }

    public ProductValidator getProductValidator() {
        return productValidator;
    }

    public ImageWriterService getImageWriterService() {
        return imageWriterService;
    }

    public ProductGroupValidator getProductGroupValidator() {
        return productGroupValidator;
    }

    public PutItemService getPutItemService() {
        return putItemService;
    }

    public ProductService getProductServiceImpl() {
        return productServiceImpl;
    }

    public EmailParser getEmailParser() {
        return emailParser;
    }

    public FloorParser getFloorParser() {
        return floorParser;
    }

    public HouseParser getHouseParser() {
        return houseParser;
    }

    public NameParser getNameParser() {
        return nameParser;
    }

    public PasswordParser getPasswordParser() {
        return passwordParser;
    }

    public PhoneParser getPhoneParser() {
        return phoneParser;
    }

    public PorchParser getPorchParser() {
        return porchParser;
    }

    public RoleParser getRoleParser() {
        return roleParser;
    }

    public RoomParser getRoomParser() {
        return roomParser;
    }

    public StreetParser getStreetParser() {
        return streetParser;
    }

    public UsernameParser getUsernameParser() {
        return usernameParser;
    }

    public SurnameParser getSurnameParser() {
        return surnameParser;
    }
}
