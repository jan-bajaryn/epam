package by.epam.cafe.service.db.impl;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.service.DatabaseManager;
import by.epam.cafe.service.db.DeliveryInfService;
import by.epam.cafe.service.exception.ServiceException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.testng.Assert.*;

public class DeliveryInfServiceImplTest {

    private final DeliveryInfService deliveryInfService = new DeliveryInfServiceImpl();
    private final DatabaseManager databaseManager = new DatabaseManager();
    private final OrderServiceImpl orderServiceImpl = new OrderServiceImpl();


    DeliveryInf DI_38 = DeliveryInf.newBuilder().id(38)
            .comments("Очень нужно конкретно к этому времени без опозданий.")
            .deliveryTime(LocalDateTime.now().withYear(2020)
                    .withMonth(3)
                    .withDayOfMonth(11)
                    .withHour(4)
                    .withMinute(42)
                    .withSecond(57)
                    .withNano(611000000)
            )
            .email("asdasd@gmail.com")
            .order(null)
            .floor(2)
            .phone("294444455")
            .house("2")
            .porch(2)
            .room("4")
            .street("Барадино")
            .build();

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testFindAllEqualsCount() throws ServiceException {
        List<Order> all = orderServiceImpl.findAll();
        int count = (int) all.stream()
                .filter(order -> order.getDeliveryInf() != null)
                .count();
        assertEquals(deliveryInfService.findAll().size(), count);
    }

    @Test
    public void testEmptyAll() throws ServiceException {
        List<DeliveryInf> all = deliveryInfService.findAll();
        for (DeliveryInf deliveryInf : all) {
            assertNull(deliveryInf.getOrder());
        }
    }

    @Test
    public void testFindEntityById() throws ServiceException {
        DeliveryInf entityById = deliveryInfService.findEntityById(38);
        assertEquals(entityById, DI_38);
    }

    @Test
    public void testMinusValueEntityById() throws ServiceException {
        assertNull(deliveryInfService.findEntityById(-1));
    }

    @Test
    public void testNotExistValue() throws ServiceException {
        assertNull(deliveryInfService.findEntityById(1000000));
    }

    @Test
    public void testDeleteById() throws ServiceException {
        try {
            for (int i = -3; i < 3; i++) {
                assertFalse(deliveryInfService.deleteById(38));
            }
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDelete() throws ServiceException {
        try {
            for (int i = -3; i < 3; i++) {
                assertFalse(deliveryInfService.delete(DeliveryInf.newBuilder().id(38).build()));
            }
        } finally {
            databaseManager.reset();
        }
    }

    @DataProvider(name = "creation")
    public Object[] creationProvider
            () {
        return new Object[][]{
                {
                        DeliveryInf.newBuilder()
                                .order(null)
                                .street("Макарова")
                                .room("4A")
                                .house("8")
                                .email("abc@gmail.com")
                                .phone("345543345")
                                .comments(null)
                                .floor(45)
                                .deliveryTime(LocalDateTime.now())
                                .porch(4)
                                .build(),
                        true
                },
                {
                        DeliveryInf.newBuilder()
                                .porch(5)
                                .deliveryTime(LocalDateTime.now())
                                .floor(4)
                                .phone("555444333")
                                .email("abceii@gmail.com")
                                .house("3A")
                                .room("4Б")
                                .street("Ленина")
                                .build(),
                        true
                },
                {
                        DeliveryInf.newBuilder()
                                .porch(null)
                                .deliveryTime(LocalDateTime.now())
                                .floor(null)
                                .phone("555444333")
                                .email("abceii@gmail.com")
                                .house("3A")
                                .room("4Б")
                                .street("Ленина")
                                .build(),
                        true
                },
                {
                        DeliveryInf.newBuilder()
                                .porch(null)
                                .deliveryTime(LocalDateTime.now())
                                .floor(null)
                                .phone("555444333")
                                .email("abceii@gmail.com")
                                .house(null)
                                .room("4Б")
                                .street("Ленина")
                                .build(),
                        false
                }

        };
    }

    @Test(description = "",
            dataProvider = "creation")
    public void testCreate(DeliveryInf input, Boolean result) throws ServiceException {
        try {
            DeliveryInf deliveryInf = deliveryInfService.create(input);
            assertEquals(deliveryInf != null, (boolean) result);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testNullCreate() {
        try {
            assertThrows(NullPointerException.class, () -> deliveryInfService.create(null));
        } finally {
            databaseManager.reset();
        }
    }

    @DataProvider(name = "update")
    public Object[][] updateProvider
            () {
        return new Object[][]{
                {
                        DeliveryInf.newBuilder()
                                .id(1000)
                                .porch(null)
                                .deliveryTime(LocalDateTime.now().plus(Duration.ofMinutes(30)))
                                .floor(null)
                                .phone("555444333")
                                .email("abceii@gmail.com")
                                .house("3A")
                                .room("4Б")
                                .street("Ленина")
                                .build()
                        ,
                        false
                },
                {
                        DeliveryInf.newBuilder()
                                .id(-1)
                                .porch(null)
                                .deliveryTime(LocalDateTime.now().plus(Duration.ofMinutes(30)))
                                .floor(null)
                                .phone("555444333")
                                .email("abceii@gmail.com")
                                .house("3A")
                                .room("4Б")
                                .street("Ленина")
                                .build(),
                        false
                },
                {
                        DeliveryInf.newBuilder()
                                .id(3)
                                .porch(1)
                                .deliveryTime(LocalDateTime.now().plus(Duration.ofMinutes(30)))
                                .floor(3)
                                .phone("555444333")
                                .email("abceii@gmail.com")
                                .house("3A")
                                .room("4Б")
                                .street("Ленина")
                                .build(),
                        true
                }
        };
    }

    @Test(description = "",
            dataProvider = "update")
    public void testUpdate(DeliveryInf deliveryInf, Boolean result) throws ServiceException {
        try {
            assertEquals(deliveryInfService.update(deliveryInf), (boolean) result);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdateNullId() {
        DeliveryInf deliveryInf = DeliveryInf.newBuilder()
                .porch(null)
                .deliveryTime(LocalDateTime.now())
                .floor(null)
                .phone("555444333")
                .email("abceii@gmail.com")
                .house("3A")
                .room("4Б")
                .street("Ленина")
                .build();

        assertThrows(NullPointerException.class, () -> deliveryInfService.update(deliveryInf));
    }
}