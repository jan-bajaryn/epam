package by.epam.cafe.service;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductGroupServiceTest {

    private ProductGroupService productGroupService = new ProductGroupService();

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