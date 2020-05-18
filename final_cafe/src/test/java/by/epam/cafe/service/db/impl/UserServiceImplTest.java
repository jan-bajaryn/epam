package by.epam.cafe.service.db.impl;

import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.service.DatabaseManager;
import by.epam.cafe.service.exception.IllegalIdException;
import by.epam.cafe.service.exception.ServiceException;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public void testNullInput() throws ServiceException {
        assertNull(userService.findEntityById(null));
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
    public void testDeleteById() throws ServiceException {
        try {
            boolean result = userService.deleteById(1);
            assertTrue(result);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDeleteNotExisting() throws ServiceException {
        try {
            boolean result = userService.deleteById(1000);
            assertFalse(result);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDeleteMinus() throws ServiceException {
        try {
            boolean result = userService.deleteById(-1000);
            assertFalse(result);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testDelete() throws ServiceException {
        try {
            boolean result = userService.delete(ADMIN);
            assertTrue(result);
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreateNull() throws ServiceException {
        try {
            assertNull(userService.create(null));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreate() throws ServiceException {
        try {
            User user = User.newBuilder()
                    .creation(LocalDateTime.now())
                    .email("abc@gmail.com")
                    .floor(2)
                    .house("4A")
                    .isBlocked(false)
                    .name("Anakonda")
                    .password("Ack4DFG")
                    .phone("333444222")
                    .porch(2)
                    .room("1C")
                    .street("Lenina")
                    .surname("Vasilev")
                    .role(Role.CLIENT)
                    .username("vitala")
                    .build();
            assertNotNull(userService.create(user));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testCreateWithoutRole() throws ServiceException {
        try {
            User user = User.newBuilder()
                    .creation(LocalDateTime.now())
                    .email("abc@gmail.com")
                    .floor(2)
                    .house("4A")
                    .isBlocked(false)
                    .name("Anakonda")
                    .password("Ack4DFG")
                    .phone("333444222")
                    .porch(2)
                    .room("1C")
                    .street("Lenina")
                    .surname("Vasilev")
                    .role(null)
                    .username("vitala")
                    .build();
            assertNull(userService.create(user));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdateNullId() throws ServiceException {
        try {
            User user = User.newBuilder()
                    .creation(LocalDateTime.now())
                    .email("abc@gmail.com")
                    .floor(2)
                    .house("4A")
                    .isBlocked(false)
                    .name("Anakonda")
                    .password("Ack4DFG")
                    .phone("333444222")
                    .porch(2)
                    .room("1C")
                    .street("Lenina")
                    .surname("Vasilev")
                    .role(null)
                    .username("vitala")
                    .build();
            assertFalse(userService.update(user));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdate() throws ServiceException {
        try {
            User user = User.newBuilder()
                    .id(16)
                    .creation(LocalDateTime.now())
                    .email("abc@gmail.com")
                    .floor(2)
                    .house("4A")
                    .role(Role.OPERATOR)
                    .isBlocked(false)
                    .name("Anakonda")
                    .password("Ack4DFG")
                    .phone("333444222")
                    .porch(2)
                    .room("1C")
                    .street("Lenina")
                    .surname("Vasilev")
                    .username("vitala")
                    .build();
            assertTrue(userService.update(user));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUpdateNullRole() throws ServiceException {
        try {
            User user = User.newBuilder()
                    .id(16)
                    .creation(LocalDateTime.now())
                    .email("abc@gmail.com")
                    .floor(2)
                    .house("4A")
                    .role(null)
                    .isBlocked(false)
                    .name("Anakonda")
                    .password("Ack4DFG")
                    .phone("333444222")
                    .porch(2)
                    .room("1C")
                    .street("Lenina")
                    .surname("Vasilev")
                    .username("vitala")
                    .build();
            assertFalse(userService.update(user));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testFindAllByPart() throws ServiceException {
        List<User> allByPart = userService.findAllByPart(1);
        assertEquals(allByPart.size(), 6);
    }

    @Test
    public void testFindAllByPart2() throws ServiceException {
        List<User> allByPart = userService.findAllByPart(2);
        assertEquals(allByPart.size(), 0);
    }

    @Test
    public void testFindAllByPartMinus() throws ServiceException {
        List<User> allByPart = userService.findAllByPart(-1);
        assertEquals(allByPart.size(), 0);
    }


    @Test
    public void testFindUserByUsernameNotExisting() throws ServiceException {
        User notExisting = userService.findUserByUsername("notexisting");
        assertNull(notExisting);
    }

    @Test
    public void testFindUserByUsername() throws ServiceException {
        User notExisting = userService.findUserByUsername("Alex");
        assertNotNull(notExisting);
    }

    @Test
    public void testBlockByIdNull() {
        try {

            assertThrows(IllegalIdException.class, () -> userService.blockById(null));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testBlockByIdMinus() {
        try {
            assertThrows(IllegalIdException.class, () -> userService.blockById(-1));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUnBlockByIdNull() {
        try {
            assertThrows(IllegalIdException.class, () -> userService.unBlockById(null));
        } finally {
            databaseManager.reset();
        }
    }

    @Test
    public void testUnBlockByIdMinus() {
        try {
            assertThrows(IllegalIdException.class, () -> userService.unBlockById(-1));
        } finally {
            databaseManager.reset();
        }
    }

}