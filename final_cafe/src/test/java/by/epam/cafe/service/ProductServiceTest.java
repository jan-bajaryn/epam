package by.epam.cafe.service;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductServiceTest {
private ProductService productService = new ProductService();
    @Test
    public void testFindAll() {
        System.out.println("productService.findAll() = " + productService.findAll());
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