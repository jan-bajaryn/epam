package by.epam.cafe.service.impl;

import by.epam.cafe.entity.impl.DeliveryInf;
import by.epam.cafe.service.DeliveryInfService;
import org.testng.annotations.Test;

import java.util.List;

public class DeliveryInfServiceImplTest {
    private final DeliveryInfService deliveryInfService = new DeliveryInfServiceImpl();

    @Test
    public void testFindAll() {
        List<DeliveryInf> all = deliveryInfService.findAll();
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