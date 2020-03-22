package by.epam.cafe.service;

import by.epam.cafe.service.impl.DeliveryInfServiceImpl;
import org.testng.annotations.Test;

public class DeliveryInfServiceImplTest {
    private DeliveryInfService deliveryInfService = new DeliveryInfServiceImpl();

    @Test
    public void testFindAll() {
        System.out.println("deliveryInfService.findAll() = " + deliveryInfService.findAll());
    }

    @Test
    public void testFindEntityById() {
        System.out.println("deliveryInfService.findEntityById(1) = " + deliveryInfService.findEntityById(1));
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