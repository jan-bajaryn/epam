package by.epam.cafe.service.validator;

import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static org.testng.Assert.*;

public class ProductGroupValidatorTest {

    @Test
    public void testIsValid() {
        /*language=RegExp*/
        String s = "[а-яА-Я\\s\\d]{1,30}";

        boolean res = Pattern.compile(s, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE).matcher("чень").matches();
        assertTrue(res);

    }
}