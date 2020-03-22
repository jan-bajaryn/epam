package by.epam.cafe.service;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderServiceTest {

    private OrderService orderService = new OrderService();

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testFindAll() {
        System.out.println("orderService.findAll() = " + orderService.findAll());
    }

    @Test
    public void testFindEntityById() {
        System.out.println("orderService.findEntityById(1) = " + orderService.findEntityById(1));
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