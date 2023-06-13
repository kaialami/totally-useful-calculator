package test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.model.Calculator;


public class CalculatorTest {
    static private double delta = 0;

    private Calculator calc;

    @BeforeClass
    static public void printClass() {
        System.out.println("CalculatorTest: ");
    }
    
    @Before
    public void setup() {
        calc = new Calculator();
    }


    @Test
    public void testConstructor() {
        assertEquals("", calc.getExpression());
        assertEquals(0, calc.getResult(), delta);;
        System.out.println("    - testConstructor passed");
    }

    @Test
    public void testUpdateNumberInput_Success() {
        calc.update("1");
        assertEquals("1", calc.getExpression());
        assertEquals(1, calc.getResult(), delta);

        calc.update("12.2");
        assertEquals("12.2", calc.getExpression());
        assertEquals(12.2, calc.getResult(), delta);

        calc.update("-44.4561");
        assertEquals("-44.4561", calc.getExpression());
        assertEquals(-44.4561, calc.getResult(), delta);

        System.out.println("    - testUpdateNumberInput_Success passed");
    }

    @Test
    public void testUpdateNumberInput_Fail() {
        try {
            calc.update("www");
            fail("Expected exception was not thrown.");
        } catch (Exception e) {
            assertEquals("www", calc.getExpression());
            assertEquals(0, calc.getResult(), delta);
        }


        System.out.println("    - testUpdateNumberInput_Fail passed");

    }


    @Test
    public void testUpdateExpressionInput_Success() {
        calc.update("1 + 0");
        assertEquals("1 + 0", calc.getExpression());
        assertEquals(1, calc.getResult(), delta);

        calc.update("5*4");
        assertEquals("5*4", calc.getExpression());
        assertEquals(20, calc.getResult(), delta);

        calc.update("-2*3+2");
        assertEquals("-2*3+2", calc.getExpression());
        assertEquals(-4, calc.getResult(), delta);

        calc.update("2(3)");
        assertEquals("2(3)", calc.getExpression());
        assertEquals(6, calc.getResult(), delta);

        calc.update("-sqrt(4)");
        assertEquals("-sqrt(4)", calc.getExpression());
        assertEquals(-2, calc.getResult(), delta);

        calc.update("11/11");
        assertEquals("11/11", calc.getExpression());
        assertEquals(1, calc.getResult(), delta);

        calc.update("10/4");
        assertEquals("10/4", calc.getExpression());
        assertEquals(2.5, calc.getResult(), delta);


        System.out.println("    - testUpdateExpressionInput_Success passed");
    }


    @Test
    public void testUpdateExpressionInput_Fail() {
        try {
            calc.update("()");
            fail();
        } catch (Exception e) {
            assertEquals("()", calc.getExpression());
            assertEquals(0, calc.getResult(), delta);
        }

        try {
            calc.update("10/0");
            fail();
        } catch (Exception e) {
            assertEquals("10/0", calc.getExpression());
            assertEquals(0, calc.getResult(), delta);
        }


        System.out.println("    - testUpdateExpressionInput_Fail passed");
    }

}