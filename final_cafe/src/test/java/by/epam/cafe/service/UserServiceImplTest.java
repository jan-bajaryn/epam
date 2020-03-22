package by.epam.cafe.service;

import by.epam.cafe.service.impl.UserServiceImpl;
import org.testng.annotations.Test;

public class UserServiceImplTest {

    private by.epam.cafe.service.UserService userService = new UserServiceImpl();

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