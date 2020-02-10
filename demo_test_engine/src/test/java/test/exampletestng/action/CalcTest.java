package test.exampletestng.action;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.training.exampletestng.action.Calc;

public class CalcTest {

    private Calc calc = new Calc();

    @DataProvider(name = "positiveDataForSqrt")
    public Object[][] createPositiveDataForSqrt() {
        return new Object[][]{
                {4.0, 2.0},
                {100.0, 10.0},
                {0.36, 0.6},
                {0.0, 0.0},
                {1.0, 1.0},
                {Integer.MAX_VALUE, 46340.950001051984}
        };
    }



    @DataProvider(name = "input_a_negative")
    public Object[] createNegativeDataFor_sqrt() {
        return new Object[]{-4.0,-100.0,-0.36};
    }

    @DataProvider(name = "input_a_b")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {new int[]{2, 3}, 5},
                        {new int[]{1, 1}, 2},
                        {new int[]{0, 0}, 0},
                };
    }
    @DataProvider(name = "dataForSum")
    public Object[][] createDataForSum(){
        return new Object[][]{
                {new int[]{3, 5}, 8},
                {new int[]{-3, 3}, 0},
                {new int[]{0, 0}, 0},
                {new int[]{-3, -5}, -8},
                {new int[]{-300, 200}, -100},
                {new int[]{10, 0}, 10}
        };

    }
    @Test(description = "Positive scenary of the sum calculation",
            dataProvider = "input_a_b")
    public void testSum(int ab[],  int c) {
        int actual = calc.sum(ab[0],ab[1]);
        int expected = c;
        assertEquals(actual, expected);

    }
    @Test
    public void testTest() {
        String expected = "TestNG is working fine";
        String actual = "TestNG is working fine";
        assertEquals(actual, expected);
    }



//(dataProvider = "input_a_negative" )



//    @Test(dataProvider = "input_a", dependsOnMethods = "sqrtNegativeDataTest")
//    public void sqrtPositiveDateTest(double a, double c) throws Exception{
//        double expected = c;
//        double actual = calc.sqrt(a);
//        assertEquals(actual, expected);
//    }


    @Test(description = "Positive scenario of the sqrt",
            dataProvider = "positiveDataForSqrt")
    public void sqrtTest(double a, double c) throws Exception{
        double actual = calc.sqrt(a);
        assertEquals(actual, c);
    }
    @Test(dataProvider = "input_a_negative" )
    public void sqrtNegativeDataTest(double a){
        assertThrows(Exception.class,()-> new Calc().sqrt(a));
    }

    @Test(description = "check the sum of 2 numbers", dataProvider = "dataForSum")
    public void sumTest(int ab[],  int c){
        int actual = calc.sum(ab[0],ab[1]);
        int expected = c;
        assertEquals(actual, expected);
    }

}
