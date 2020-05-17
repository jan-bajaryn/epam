package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommentsValidatorTest {

    private final CommentsValidator commentsValidator = new CommentsValidator();
    private static final String MORE_THAN_200_INPUT = "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj ";

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Some text", true},
                {"", true},
                {null, true},
                {"Какой-то текст", true},
                {MORE_THAN_200_INPUT, false},
        };
    }

    @Test(description = "Test for CommentsValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(commentsValidator.isValid(input), (boolean) result);
    }
}