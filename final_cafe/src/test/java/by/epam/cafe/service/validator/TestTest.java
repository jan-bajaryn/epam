package by.epam.cafe.service.validator;

import by.epam.cafe.service.DatabaseManager;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TestTest {
    @Test
    public void mytest() {
        new DatabaseManager().reset();
    }
}
