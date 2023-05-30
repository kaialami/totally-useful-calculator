package test.model;

import static org.junit.Assert.assertEquals;

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
    public void testUpdate() {
        calc.update("1");
        assertEquals("1", calc.getExpression());
        assertEquals(1, calc.getResult(), delta);

        // calc.update("1 + 0");
        // assertEquals("1 + 0", calc.getExpression());
        // assertEquals(1, calc.getResult(), delta);

        System.out.println("    - testUpdate passed");
    }
}