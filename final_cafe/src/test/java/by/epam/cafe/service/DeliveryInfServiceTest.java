package by.epam.cafe.service;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DeliveryInfServiceTest {
    private DeliveryInfService deliveryInfService = new DeliveryInfService();

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