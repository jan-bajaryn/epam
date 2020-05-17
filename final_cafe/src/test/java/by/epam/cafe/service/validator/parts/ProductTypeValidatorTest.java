package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.entity.enums.ProductType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductTypeValidatorTest {

    private final ProductTypeValidator productTypeValidator = new ProductTypeValidator();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false},
                {ProductType.PIZZA, true},
                {ProductType.DESSERT, true},
                {ProductType.DRINK, true},
                {ProductType.SNACK, true},
        };
    }

    @Test(description = "Test for ProductTypeValidator",
            dataProvider = "check")
    public void checkInput(ProductType input, Boolean result) {
        assertEquals(productTypeValidator.isValid(input), (boolean) result);
    }

}