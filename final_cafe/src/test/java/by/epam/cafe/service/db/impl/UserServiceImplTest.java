package by.epam.cafe.service.db.impl;

import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.DatabaseManager;
import by.epam.cafe.service.exception.ServiceException;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.testng.Assert.*;

public class UserServiceImplTest {
private final DatabaseManager databaseManager = new DatabaseManager();

    private UserServiceImpl userService = new UserServiceImpl();
    private static final User ADMIN = User.newBuilder()
            .id(1)
            .creation(Timestamp.valueOf("2020-03-13 15:52:57.567086").toLocalDateTime())
            .name("Vania")
            .password("abcdefg")
            .phone("294441111")
            .role(Role.values()[0])
            .surname("Ivanov")
            .username("mister")
            .email("vania@mail.ru")
            .floor(3)
            .house("11/2")
            .porch(1)
            .room("10")
            .street("Lenina")
            .isBlocked(false)
            .build();

    @Test(description = "Check expected size of the return collection")
    public void testFindAll() throws ServiceException {
        assertEquals(userService.findAll().size(), userService.count());
    }

    @Test(description = "Check not null fields of all entities")
    public void testNotNullFields() throws ServiceException {
        List<User> all = userService.findAll();
        for (User user : all) {
            assertNotNull(user.getId());
            assertNotNull(user.getUsername());
            assertNotNull(user.getPassword());
            assertNotNull(user.getRole());
            assertNotNull(user.getEmail());
        }
    }

    @Test(description = "Check if result collection contains admin")
    public void testContainsAdmin() throws ServiceException {
        List<User> all = userService.findAll();
        assertTrue(all.contains(ADMIN));
    }

    @Test(description = "Check if it can find admin")
    public void testUserReading() throws ServiceException {
        User entityById = userService.findEntityById(1);
        assertEquals(entityById, ADMIN);

    }

    @Test(description = "Check if result of null input throws NullPointerException")
    public void testNullInput() {
        assertThrows(NullPointerException.class, () -> userService.findEntityById(null));
    }

    @Test(description = "Test negative Integer input")
    public void testNegativeInput() throws ServiceException {
        User actual = userService.findEntityById(-1);
        assertNull(actual);
    }

    @Test(description = "Test not existing in database information")
    public void testNotExisting() throws ServiceException {
        assertNull(userService.findEntityById(200));
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