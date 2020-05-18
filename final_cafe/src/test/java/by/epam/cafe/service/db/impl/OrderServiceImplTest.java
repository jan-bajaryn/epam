package by.epam.cafe.service.db.impl;

import by.epam.cafe.config.Configuration;
import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.service.DatabaseManager;
import by.epam.cafe.service.exception.ServiceException;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.testng.Assert.*;

public class OrderServiceImplTest {

    private final DatabaseManager databaseManager = new DatabaseManager();
    private final OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
    private final DeliveryInfServiceImpl deliveryInfServiceImpl = new DeliveryInfServiceImpl();

    @Test
    public void testFindAll() throws ServiceException {
        assertEquals(orderServiceImpl.findAll().size(), orderServiceImpl.count());
    }

    @Test
    public void testFindAllNotNullFields() throws ServiceException {
        List<Order> all = orderServiceImpl.findAll();
        for (Order order : all) {
            assertNotNull(order.getStatus());
            assertNotNull(order.getCreation());
            assertNotNull(order.getId());
        }
    }

    @Test
    public void testFindAllByPart() throws ServiceException {
        assertEquals(orderServiceImpl.findAllByPart(1).size(), Configuration.MAX_PAGINATION_ELEMENTS);
    }

    @Test
    public void testFindEntityById() throws ServiceException {
        Order entityById = orderServiceImpl.findEntityById(4);
        assertNotNull(entityById);
    }

    @Test
    public void testFindEntityByIdNullInput() throws ServiceException {
        Order entityById = orderServiceImpl.findEntityById(null);
        assertNull(entityById);
    }

    @Test
    public void testFindEntityByIdNotExisting() throws ServiceException {
        Order entityById = orderServiceImpl.findEntityById(1000);
        assertNull(entityById);
    }

    @Test
    public void testFindEntityByIdMinus() throws ServiceException {
        Order entityById = orderServiceImpl.findEntityById(-1000);
        assertNull(entityById);
    }


    @Test
    public void testFindCurrentByUserId() throws ServiceException {
        Order currentByUserId = orderServiceImpl.findCurrentByUserId(20);
        assertNotNull(currentByUserId);
    }

    @Test
    public void testFindCurrentByUserIdNegative() throws ServiceException {
        Order currentByUserId = orderServiceImpl.findCurrentByUserId(3);
        assertNull(currentByUserId);
    }

    @Test
    public void testFindCurrentByUserIdNotExisting() throws ServiceException {
        Order currentByUserId = orderServiceImpl.findCurrentByUserId(1000);
        assertNull(currentByUserId);
    }

    @Test
    public void testFindCurrentByUserIdNullInput() throws ServiceException {
        Order currentByUserId = orderServiceImpl.findCurrentByUserId(null);
        assertNull(currentByUserId);
    }

    @Test
    public void testFindOrCreateCurrentByUserIdNullInput() {
        try {
            assertThrows(NullPointerException.class, () -> orderServiceImpl.findOrCreateCurrentByUserId(null));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testFindOrCreateCurrentByUserIdNotExisting() {
        try {
            assertThrows(NullPointerException.class, () -> orderServiceImpl.findOrCreateCurrentByUserId(-1));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testFindOrCreateCurrentByUserId() throws ServiceException {
        try {
            Order orCreateCurrentByUserId = orderServiceImpl.findOrCreateCurrentByUserId(18);
            assertNotNull(orCreateCurrentByUserId);
        } finally {
            databaseManager.reset();
        }
    }


    @Test
    public void testDelete() throws ServiceException {
        try {
            Order entityById = orderServiceImpl.findEntityById(43);
            boolean delete = orderServiceImpl.delete(entityById);
            assertTrue(delete);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDeleteNull() throws ServiceException {
        try {
            Order entityById = orderServiceImpl.findEntityById(null);
            boolean delete = orderServiceImpl.delete(entityById);
            assertFalse(delete);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDeleteNotExisting() throws ServiceException {
        try {
            Order entityById = orderServiceImpl.findEntityById(-1);
            boolean delete = orderServiceImpl.delete(entityById);
            assertFalse(delete);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreateNull() throws ServiceException {
        try {
            Order order = orderServiceImpl.create(null);
            assertNull(order);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreateWithoutDeliveryInf() throws ServiceException {
        try {
            Order order = Order.newBuilder()
                    .status(OrderStatus.CONFIRMED)
                    .user(null)
                    .clientName("Anna")
                    .creation(LocalDateTime.now())
                    .deliveryInf(null)
                    .paymentType(PaymentType.CASH)
                    .price(1234)
                    .products(new HashMap<>() {
                                  {
                                      put(Product.newBuilder().id(3).build(), 1);
                                      put(Product.newBuilder().id(4).build(), 2);
                                  }
                              }
                    )
                    .build();
            order = orderServiceImpl.create(order);
            assertNull(order);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreate() throws ServiceException {
        try {
            Order order = Order.newBuilder()
                    .status(OrderStatus.CONFIRMED)
                    .user(null)
                    .clientName("Anna")
                    .creation(LocalDateTime.now())
                    .deliveryInf(null)
                    .paymentType(PaymentType.CASH)
                    .price(1234)
                    .deliveryInf(
                            DeliveryInf.newBuilder()
                                    .deliveryTime(LocalDateTime.now().plusMinutes(20))
                                    .email("abc@gmail.com")
                                    .floor(2)
                                    .house("2")
                                    .street("Lenina")
                                    .phone("234444555")
                                    .porch(1)
                                    .room("3A")
                                    .comments("Скорее, пожалуйста")
                                    .build()
                    )
                    .products(new HashMap<>() {
                                  {
                                      put(Product.newBuilder().id(3).build(), 1);
                                      put(Product.newBuilder().id(4).build(), 2);
                                  }
                              }
                    )
                    .build();
            order = orderServiceImpl.create(order);
            assertNotNull(order);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreateCheckResult() throws ServiceException {
        try {
            Order order = Order.newBuilder()
                    .status(OrderStatus.CONFIRMED)
                    .user(null)
                    .clientName("Anna")
                    .creation(LocalDateTime.now())
                    .deliveryInf(null)
                    .paymentType(PaymentType.CASH)
                    .price(1234)
                    .deliveryInf(
                            DeliveryInf.newBuilder()
                                    .deliveryTime(LocalDateTime.now().plusMinutes(20))
                                    .email("abc@gmail.com")
                                    .floor(2)
                                    .house("2")
                                    .street("Lenina")
                                    .phone("234444555")
                                    .porch(1)
                                    .room("3A")
                                    .comments("Скорее, пожалуйста")
                                    .build()
                    )
                    .products(new HashMap<>() {
                                  {
                                      put(Product.newBuilder().id(3).build(), 1);
                                      put(Product.newBuilder().id(4).build(), 2);
                                  }
                              }
                    )
                    .build();
            order = orderServiceImpl.create(order);

            Order entityById = orderServiceImpl.findEntityById(order.getId());
            assertEquals(entityById.getDeliveryInf().getId(), order.getDeliveryInf().getId());
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdateNull() throws ServiceException {
        try {
            assertFalse(orderServiceImpl.update(null));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdateNullId() throws ServiceException {
        try {
            Order order = Order.newBuilder()
                    .status(OrderStatus.CONFIRMED)
                    .user(null)
                    .clientName("Anna")
                    .creation(LocalDateTime.now())
                    .deliveryInf(null)
                    .paymentType(PaymentType.CASH)
                    .price(1234)
                    .deliveryInf(
                            DeliveryInf.newBuilder()
                                    .deliveryTime(LocalDateTime.now().plusMinutes(20))
                                    .email("abc@gmail.com")
                                    .floor(2)
                                    .house("2")
                                    .street("Lenina")
                                    .phone("234444555")
                                    .porch(1)
                                    .room("3A")
                                    .comments("Скорее, пожалуйста")
                                    .build()
                    )
                    .products(new HashMap<>() {
                                  {
                                      put(Product.newBuilder().id(3).build(), 1);
                                      put(Product.newBuilder().id(4).build(), 2);
                                  }
                              }
                    )
                    .build();
            boolean result = orderServiceImpl.update(order);
            assertFalse(result);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdateIllegalId() throws ServiceException {
        try {
            Order order = Order.newBuilder()
                    .id(-2)
                    .status(OrderStatus.CONFIRMED)
                    .user(null)
                    .clientName("Anna")
                    .creation(LocalDateTime.now())
                    .deliveryInf(null)
                    .paymentType(PaymentType.CASH)
                    .price(1234)
                    .deliveryInf(
                            DeliveryInf.newBuilder()
                                    .deliveryTime(LocalDateTime.now().plusMinutes(20))
                                    .email("abc@gmail.com")
                                    .floor(2)
                                    .house("2")
                                    .street("Lenina")
                                    .phone("234444555")
                                    .porch(1)
                                    .room("3A")
                                    .comments("Скорее, пожалуйста")
                                    .build()
                    )
                    .products(new HashMap<>() {
                                  {
                                      put(Product.newBuilder().id(3).build(), 1);
                                      put(Product.newBuilder().id(4).build(), 2);
                                  }
                              }
                    )
                    .build();
            boolean result = orderServiceImpl.update(order);
            assertFalse(result);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdate() throws ServiceException {
        try {
            Order order = Order.newBuilder()
                    .id(6)
                    .status(OrderStatus.CONFIRMED)
                    .user(null)
                    .clientName("Anna")
                    .creation(LocalDateTime.now())
                    .deliveryInf(null)
                    .paymentType(PaymentType.CASH)
                    .price(1234)
                    .deliveryInf(
                            DeliveryInf.newBuilder()
                                    .id(5)
                                    .deliveryTime(LocalDateTime.now().plusMinutes(20))
                                    .email("abc@gmail.com")
                                    .floor(2)
                                    .house("2")
                                    .street("Lenina")
                                    .phone("234444555")
                                    .porch(1)
                                    .room("3A")
                                    .comments("Скорее, пожалуйста")
                                    .build()
                    )
                    .products(new HashMap<>() {
                                  {
                                      put(Product.newBuilder().id(3).build(), 1);
                                      put(Product.newBuilder().id(4).build(), 2);
                                  }
                              }
                    )
                    .build();
            boolean result = orderServiceImpl.update(order);
            assertTrue(result);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdateCheckResult() throws ServiceException {
        try {
            Order order = Order.newBuilder()
                    .id(6)
                    .status(OrderStatus.CONFIRMED)
                    .user(null)
                    .clientName("Anna")
                    .creation(LocalDateTime.now())
                    .deliveryInf(null)
                    .paymentType(PaymentType.CASH)
                    .price(1234)
                    .deliveryInf(
                            DeliveryInf.newBuilder()
                                    .deliveryTime(LocalDateTime.now().plusMinutes(20))
                                    .email("abc@gmail.com")
                                    .floor(2)
                                    .house("2")
                                    .street("Lenina")
                                    .phone("234444555")
                                    .porch(1)
                                    .room("3A")
                                    .comments("Скорее, пожалуйста")
                                    .build()
                    )
                    .products(new HashMap<>() {
                                  {
                                      put(Product.newBuilder().id(3).build(), 1);
                                      put(Product.newBuilder().id(4).build(), 2);
                                  }
                              }
                    )
                    .build();
            orderServiceImpl.update(order);
            DeliveryInf entityById = deliveryInfServiceImpl.findEntityById(5);
            assertEquals(entityById.getRoom(), "3A");
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdateCheckResultWaiting() throws ServiceException {
        try {
            Order order = Order.newBuilder()
                    .id(44)
                    .status(OrderStatus.CONFIRMED)
                    .user(null)
                    .clientName("Anna")
                    .creation(LocalDateTime.now())
                    .deliveryInf(null)
                    .paymentType(PaymentType.CASH)
                    .price(1234)
                    .deliveryInf(
                            DeliveryInf.newBuilder()
                                    .deliveryTime(LocalDateTime.now().plusMinutes(20))
                                    .email("abc@gmail.com")
                                    .floor(2)
                                    .house("2")
                                    .street("Lenina")
                                    .phone("234444555")
                                    .porch(1)
                                    .room("3A")
                                    .comments("Скорее, пожалуйста")
                                    .build()
                    )
                    .products(new HashMap<>() {
                                  {
                                      put(Product.newBuilder().id(3).build(), 1);
                                      put(Product.newBuilder().id(4).build(), 2);
                                  }
                              }
                    )
                    .build();
            orderServiceImpl.update(order);
            System.out.println("order.getDeliveryInf().getId() = {}" + order.getDeliveryInf().getId());
            DeliveryInf entityById = deliveryInfServiceImpl.findEntityById(order.getDeliveryInf().getId());
            assertEquals(entityById.getRoom(), "3A");
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testPlusProduct() throws ServiceException {
        try {
            orderServiceImpl.plusProduct(44, 1);
            Order entityById = orderServiceImpl.findEntityById(44);
            Set<Map.Entry<Product, Integer>> entries = entityById.getProducts().entrySet();
            for (Map.Entry<Product, Integer> entry : entries) {
                assertEquals(entry.getKey().getId(), Integer.valueOf(1));
                assertEquals(entry.getValue(), Integer.valueOf(1));
            }
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testPlusProductNullInput() {
        try {
            assertThrows(ServiceException.class, () -> orderServiceImpl.plusProduct(44, null));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testPlusProductNotExisting() {
        try {
            assertThrows(ServiceException.class, () -> orderServiceImpl.plusProduct(44, 1000));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDeleteProduct() throws ServiceException {
        try {
            orderServiceImpl.deleteProduct(22, 15);
            Order entityById = orderServiceImpl.findEntityById(22);
            boolean result = entityById.getProducts().entrySet()
                    .stream()
                    .anyMatch(p -> p.getKey().getId().equals(15));
            assertFalse(result);
        } finally {
            databaseManager.reset();
        }
    }


    @Test
    public void testDeleteProductNotExisting() {
        try {
            assertThrows(ServiceException.class, () -> orderServiceImpl.deleteProduct(1000, 15));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDeleteProductNullInput() {
        try {
            assertThrows(ServiceException.class, () -> orderServiceImpl.deleteProduct(null, 15));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testMinusOrDelete() throws ServiceException {
        try {
            orderServiceImpl.minusOrDelete(22, 15);
            Order entityById = orderServiceImpl.findEntityById(22);
            Map.Entry<Product, Integer> entry = entityById.getProducts().entrySet()
                    .stream()
                    .filter(p -> p.getKey().getId().equals(15))
                    .findAny()
                    .orElse(null);
            assertEquals(Objects.requireNonNull(entry).getValue(), Integer.valueOf(3));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testMinusOrDeleteDeleteCase() throws ServiceException {
        try {
            orderServiceImpl.minusOrDelete(10, 12);
            Order entityById = orderServiceImpl.findEntityById(10);
            boolean match = entityById.getProducts().entrySet()
                    .stream()
                    .anyMatch(p -> p.getKey().getId().equals(12));
            assertFalse(match);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testMinusOrDeleteNullInput() {
        try {
            assertThrows(ServiceException.class, () -> orderServiceImpl.minusOrDelete(null, 12));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCancelOrDeleteByIdDeleteCase() throws ServiceException {
        try {
            orderServiceImpl.cancelOrDeleteById(44);
            Order entityById = orderServiceImpl.findEntityById(44);
            assertNull(entityById);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCancelOrDeleteByIdCancelCase() throws ServiceException {
        try {
            orderServiceImpl.cancelOrDeleteById(43);
            Order entityById = orderServiceImpl.findEntityById(43);
            assertEquals(entityById.getStatus(), OrderStatus.CANCELED);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCancelOrDeleteByIdNullInput() {
        try {
            assertThrows(ServiceException.class, () -> orderServiceImpl.cancelOrDeleteById(null));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCancelOrDeleteByIdIllegalInput() {
        try {
            assertThrows(ServiceException.class, () -> orderServiceImpl.cancelOrDeleteById(-1));
        } finally {
            databaseManager.reset();
        }
    }


}