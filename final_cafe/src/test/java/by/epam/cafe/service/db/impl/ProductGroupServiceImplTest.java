package by.epam.cafe.service.db.impl;

import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.service.DatabaseManager;
import by.epam.cafe.service.db.ProductGroupService;
import by.epam.cafe.service.exception.NullServiceException;
import by.epam.cafe.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static by.epam.cafe.config.Configuration.MAX_PAGINATION_ELEMENTS;
import static org.testng.Assert.*;

public class ProductGroupServiceImplTest {

    private final ProductGroupService productGroupService = new ProductGroupServiceImpl();
    private final DatabaseManager databaseManager = new DatabaseManager();
    private final ProductServiceImpl productServiceImpl = new ProductServiceImpl();

    @Test
    public void testFindAll() throws ServiceException {
        assertEquals(productGroupService.findAll().size(), productGroupService.count());
    }

    @Test
    public void testFindAllNotNullParams() throws ServiceException {
        List<ProductGroup> all = productGroupService.findAll();
        for (ProductGroup productGroup : all) {
            assertNotNull(productGroup.getId());
            assertNotNull(productGroup.getDescription());
            assertNotNull(productGroup.getName());
            assertNotNull(productGroup.getPhotoName());
            assertNotNull(productGroup.getType());

        }
    }

    @Test
    public void testFindAllByPart() throws ServiceException {
        List<ProductGroup> allByPart = productGroupService.findAllByPart(1);
        assertEquals(allByPart.size(), MAX_PAGINATION_ELEMENTS);
    }

    @Test
    public void testFindEntityById() throws ServiceException {
        ProductGroup entityById = productGroupService.findEntityById(1);
        assertNotNull(entityById);
    }

    @Test
    public void testFindEntityByIdNotExists() throws ServiceException {
        ProductGroup entityById = productGroupService.findEntityById(1000);
        assertNull(entityById);
    }

    @Test
    public void testFindAllByProductTypeNotDisabledNullINput() throws ServiceException {
        assertThrows(ServiceException.class, () -> productGroupService.findAllByProductTypeNotDisabled(null));
    }

    @DataProvider(name = "byType")
    public Object[][] byTypeProvider
            () throws ServiceException {
        List<ProductGroup> forSnack = productGroupService.findAll().stream()
                .filter(p -> p.getType() == ProductType.SNACK)
                .filter(p -> !p.isDisabled())
                .collect(Collectors.toList());
        List<ProductGroup> forPizza = productGroupService.findAll().stream()
                .filter(p -> p.getType() == ProductType.PIZZA)
                .filter(p -> !p.isDisabled())
                .collect(Collectors.toList());
        List<ProductGroup> forDrink = productGroupService.findAll().stream()
                .filter(p -> p.getType() == ProductType.DRINK)
                .filter(p -> !p.isDisabled())
                .collect(Collectors.toList());
        List<ProductGroup> forDessert = productGroupService.findAll().stream()
                .filter(p -> p.getType() == ProductType.DESSERT)
                .filter(p -> !p.isDisabled())
                .collect(Collectors.toList());
        return new Object[][]{
                {ProductType.SNACK, forSnack},
                {ProductType.PIZZA, forPizza},
                {ProductType.DRINK, forDrink},
                {ProductType.DESSERT, forDessert},
        };
    }

    @Test(description = "",
            dataProvider = "byType")
    public void testFindAllByProductTypeNotDisabled(ProductType type, List<ProductGroup> list) throws ServiceException {
        assertEquals(productGroupService.findAllByProductTypeNotDisabled(type), list);
    }

    @Test
    public void testFindAllExcept() throws ServiceException {
        final ProductGroup build = ProductGroup.newBuilder().id(1).build();
        List<ProductGroup> allExcept = productGroupService.findAllExcept(build);
        List<ProductGroup> collect = productGroupService.findAll().stream()
                .filter(p -> !p.getId().equals(build.getId()))
                .collect(Collectors.toList());
        assertEquals(allExcept, collect);
    }

    @Test
    public void testDisableById() throws ServiceException {
        try {
            productGroupService.disableById(1);
            ProductGroup entityById = productGroupService.findEntityById(1);
            assertTrue(entityById.isDisabled());
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDisableByIdNull() {
        try {
            assertThrows(NullServiceException.class, () -> productGroupService.disableById(null));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testEnableById() throws ServiceException {
        try {
            productGroupService.enableById(31);
            ProductGroup entityById = productGroupService.findEntityById(31);
            assertFalse(entityById.isDisabled());
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testEnableByIdNull() {
        try {
            assertThrows(NullServiceException.class, () -> productGroupService.enableById(null));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreateNull() throws ServiceException {
        try {
            ProductGroup productGroup = productGroupService.create(null);
            assertNull(productGroup);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreate() throws ServiceException {
        try {
            ProductGroup build = ProductGroup.newBuilder()
                    .description("Some description")
                    .name("Some name")
                    .photoName("Hahaha.jpg")
                    .type(ProductType.PIZZA)
                    .products(new ArrayList<>(Collections.singletonList(Product.newBuilder().id(24).build())))
                    .build();
            ProductGroup productGroup = productGroupService.create(build);
            assertNotNull(productGroup);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreateCheckProduct() throws ServiceException {
        try {
            ProductGroup build = ProductGroup.newBuilder()
                    .description("Some description")
                    .name("Some name")
                    .photoName("Hahaha.jpg")
                    .type(ProductType.PIZZA)
                    .products(new ArrayList<>(Collections.singletonList(Product.newBuilder().id(24).build())))
                    .build();
            ProductGroup productGroup = productGroupService.create(build);
            Product entityById = productServiceImpl.findEntityById(24);
            assertEquals(productGroup.getId(), entityById.getProductGroup().getId());
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdateNull() throws ServiceException {
        try {
            boolean result = productGroupService.update(null);
            assertFalse(result);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdate() throws ServiceException {
        try {
            ProductGroup entityById = productGroupService.findEntityById(31);
            entityById.getProducts().add(Product.newBuilder().id(24).build());
            boolean result = productGroupService.update(entityById);
            assertTrue(result);
        } finally {
            databaseManager.reset();
        }
    }


    @Test
    public void testUpdateResultInDb() throws ServiceException {
        try {
            ProductGroup entityById = productGroupService.findEntityById(31);
            entityById.getProducts().add(Product.newBuilder().id(24).build());
            productGroupService.update(entityById);
            entityById = productGroupService.findEntityById(31);
            boolean match = entityById.getProducts().stream()
                    .anyMatch(p -> p.getId().equals(24));

            assertTrue(match);
        } finally {
            databaseManager.reset();
        }
    }


}