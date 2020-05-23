package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.testng.Assert.*;

public class TimeValidatorTest {
    private final TimeValidator timeValidator = TimeValidator.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false},
                {LocalDateTime.now(), false},
                {LocalDateTime.now().minus(Duration.ofMinutes(100)), false},
                {LocalDateTime.now().minus(Duration.ofMinutes(200)), false},
                {LocalDateTime.now().minus(Duration.ofDays(2)), false},
                {LocalDateTime.now().minus(Duration.ofHours(25)), false},
                {LocalDateTime.now().plus(Duration.ofMinutes(100)), true},
                {LocalDateTime.now().plus(Duration.ofMinutes(200)), true},
        };
    }

    @Test(description = "Test for TimeValidator",
            dataProvider = "check")
    public void testIsValid(LocalDateTime value, Boolean result) {
        assertEquals(timeValidator.isValid(value), (boolean) result);
    }

}