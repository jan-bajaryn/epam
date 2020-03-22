package by.epam.cafe.service;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserServiceTest {

    private UserService userService = new UserService();

    @Test
    public void testFindAll() {
        System.out.println("userService.findAll() = " + userService.findAll());
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