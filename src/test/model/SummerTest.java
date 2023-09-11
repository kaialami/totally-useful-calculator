package test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.model.Calculator;
import main.model.Summer;

public class SummerTest {

    static private double delta = 0;

    private Summer summer;

    @BeforeClass
    static public void printClass() {
        System.out.println("SummerTest: ");
    }

    @Before
    public void setup() {
        summer = new Summer();
    }

    @Test
    public void testConstructor() {
        Calculator calc = summer.getCalculator();
        assertEquals(calc.getExpression(), "");
        assertEquals(calc.getResult(), 0, delta);
        assertEquals(summer.getExpression(), "");
        assertEquals(summer.getResult(), 0, delta);
        assertEquals(summer.getEvent(), 0);
        assertEquals(summer.getPrevEvent(), 0);
        assertEquals(summer.getReply(), Summer.GREETING);
        
        System.out.println("    - testConstructor passed");
    }

    @Test
    public void testBecomeLonger() {
        summer.becomeLonger("1");
        assertEquals(summer.getExpression(), "1");

        summer.becomeLonger("+");
        assertEquals(summer.getExpression(), "1+");

        summer.becomeLonger(")");
        assertEquals(summer.getExpression(), "1+)");



        System.out.println("    - testBecomeLonger passed");
    }

    @Test
    public void testBecomeLongerTooLong() {
        for (int i = 0; i < Summer.EXPRESSION_LENGTH; i++) {
            summer.becomeLonger("0");
        }
        summer.becomeLonger("+");
        assertEquals(summer.getExpression(), "0000000000");
 
        System.out.println("    - testBecomeLongerTooLong passed");

    }


    @Test
    public void testClear() {
        summer.becomeLonger("1");
        summer.clear();
        assertEquals(summer.getExpression(), "");

        System.out.println("    - testClear passed");
    }

    private void updateSetup(int event) {
        summer.setEvent(event);
        summer.becomeLonger("1");
        summer.becomeLonger("+");
        summer.becomeLonger("2");
        summer.update();
    }


    @Test
    public void testUpdate0() {
        updateSetup(0);

        assertEquals(summer.getExpression(), "1+2");
        assertEquals(summer.getResult(), 3, delta);
        System.out.println("        event 0: " + summer.getReply());
        summer.clear();

        summer.setEvent(0);
        summer.becomeLonger("1");
        summer.becomeLonger("+");
        summer.becomeLonger("2");
        summer.becomeLonger("/");
        summer.becomeLonger("4");
        summer.update();
        assertEquals(summer.getExpression(), "1+2/4");
        assertEquals(summer.getResult(), 1.5, delta);
        System.out.println("        event 0: " + summer.getReply());

        System.out.println("    - testUpdate0 passed");
    }

    @Test
    public void testUpdate1to6() {
        int sum = 3;
        for (int i = 1; i <= 6; i++) {
            updateSetup(i);
            assertEquals(summer.getExpression(), "1+2");
            assertEquals(summer.getResult(), sum + i, delta);
            System.out.println("        event " + i + ": " + summer.getReply());
            summer.clear();
        }

        System.out.println("    - testUpdate1to6 passed");
    }

    @Test
    public void testUpdate7() {
        updateSetup(7);
        // assertEquals
    }

}
