package by.epam.cafe.service;

import by.epam.cafe.service.impl.ProductGroupServiceImpl;
import org.testng.annotations.Test;

public class ProductGroupServiceImplTest {

    private ProductGroupService productGroupService = new ProductGroupServiceImpl();

    @Test
    public void testFindAll() {
        System.out.println("productGroupService.findAll() = " + productGroupService.findAll());
    }

    @Test
    public void testFindEntityById() {
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