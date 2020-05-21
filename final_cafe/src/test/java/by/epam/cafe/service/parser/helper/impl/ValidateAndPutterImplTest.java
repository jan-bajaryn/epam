package by.epam.cafe.service.parser.helper.impl;

import by.epam.cafe.entity.struct.OptionalNullable;
import by.epam.cafe.service.parser.helper.ValidateAndPutter;
import org.testng.annotations.Test;

import java.util.HashMap;

import static by.epam.cafe.service.parser.helper.impl.ValidateAndPutterImpl.POSTFIX;
import static org.testng.Assert.*;

public class ValidateAndPutterImplTest {
    private final ValidateAndPutter validateAndPutter = ValidateAndPutterImpl.getInstance();

    @Test
    public void testValidateAndPutTrue() {
        boolean result =
                validateAndPutter.validateAndPut(new HashMap<>(), OptionalNullable.empty(), "abc", "param");
        assertFalse(result);

    }

    @Test
    public void testValidateAndPutFalse() {
        boolean result =
                validateAndPutter.validateAndPut(new HashMap<>(), OptionalNullable.of(1), "abc", "param");
        assertTrue(result);
    }

    @Test
    public void testValidateAndPutCheckResultsEmptyContains() {
        HashMap<String, String> redirect = new HashMap<>();
        String label = "abc";
        String param = "param";
        validateAndPutter.validateAndPut(redirect, OptionalNullable.empty(), label, param);
        String paramEquals = redirect.get(label);
        assertEquals(paramEquals, param);
    }

    @Test
    public void testValidateAndPutCheckResultsEmptyContainsErr() {
        HashMap<String, String> redirect = new HashMap<>();
        String label = "abc";
        String param = "param";
        validateAndPutter.validateAndPut(redirect, OptionalNullable.empty(), label, param);
        String result = redirect.get(label + POSTFIX);
        assertEquals(result, "true");
    }

    @Test
    public void testValidateAndPutCheckResultsFullContains() {
        HashMap<String, String> redirect = new HashMap<>();
        String label = "abc";
        String param = "param";
        validateAndPutter.validateAndPut(redirect, OptionalNullable.of(1), label, param);
        String paramEquals = redirect.get(label);
        assertEquals(paramEquals, param);
    }

    @Test
    public void testValidateAndPutCheckResultsFullNotContains() {
        HashMap<String, String> redirect = new HashMap<>();
        String label = "abc";
        String param = "param";
        validateAndPutter.validateAndPut(redirect, OptionalNullable.of(1), label, param);
        String paramEquals = redirect.get(label + POSTFIX);
        assertNull(paramEquals);
    }
}