package by.epam.cafe.service;

import by.epam.cafe.service.impl.OrderServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderServiceImplTest {

    private OrderServiceImpl orderServiceImpl = new OrderServiceImpl();

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testFindAll() {
        System.out.println("orderService.findAll() = " + orderServiceImpl.findAll());
    }

    @Test
    public void testFindEntityById() {
        System.out.println("orderService.findEntityById(1) = " + orderServiceImpl.findEntityById(1));
    }

    @Test
    public void testDeleteById() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testCreate() {
    }

    @Test
    public void testUpdate() {
    }
}