package test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

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
        
        System.out.println("    - testConstructor passed\n");
    }

    @Test
    public void testBecomeLonger() {
        summer.becomeLonger("1");
        assertEquals(summer.getExpression(), "1");

        summer.becomeLonger("+");
        assertEquals(summer.getExpression(), "1+");

        summer.becomeLonger(")");
        assertEquals(summer.getExpression(), "1+)");



        System.out.println("    - testBecomeLonger passed\n");
    }

    @Test
    public void testBecomeLongerTooLong() {
        for (int i = 0; i < Summer.EXPRESSION_LENGTH; i++) {
            summer.becomeLonger("0");
        }
        summer.becomeLonger("+");
        assertEquals(summer.getExpression(), "00000000");
 
        System.out.println("    - testBecomeLongerTooLong passed\n");

    }


    @Test
    public void testClear() {
        summer.becomeLonger("1");
        summer.clear();
        assertEquals(summer.getExpression(), "");

        System.out.println("    - testClear passed\n");
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
        assertFalse(summer.getEvent() == 0);
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
        assertFalse(summer.getEvent() == 0);
        System.out.println("        event 0: " + summer.getReply());

        System.out.println("    - testUpdate0 passed\n");
    }

    @Test
    public void testUpdate1to6() {
        int sum = 3;
        for (int i = 1; i <= 6; i++) {
            updateSetup(i);
            assertEquals(summer.getExpression(), "1+2");
            assertEquals(summer.getResult(), sum + i, delta);
            assertFalse(summer.getEvent() == i);
            System.out.println("        event " + i + ": " + summer.getReply());
            summer.clear();
        }

        System.out.println("    - testUpdate1to6 passed\n");
    }

    @Test
    public void testUpdate7() {
        updateSetup(7);
        assertEquals(summer.getReply(), Summer.REFUSE);
        assertFalse(summer.getEvent() == 7);
    }


    @Test
    // prints 10 different shuffles for visual test
    // only checks if no repeats (sorted map == default map)
    public void testShuffleNumMap() {
        System.out.println("    - testShuffleNumMap: ");
        for (int rep = 0; rep < 10; rep++) {
            System.out.print("          rep " + rep + "     numMap = ");
            summer.setEvent(8);     // shuffles numMap
            int[] numMap = summer.getNumMap();
            System.out.println(Arrays.toString(numMap));
            Arrays.sort(numMap);
            System.out.println("                    sorted = " + Arrays.toString(numMap)); 
            summer.setEvent(0);     // numMap set to default

            // assertEquals(numMap, Summer.DEFAULT_MAP);
        }
        System.out.println("    - testShuffleNumMap passed\n");
    }


    @Test
    public void testUpdate8() {
        System.out.println("    - testUpdate8: ");
        for (int rep = 0; rep < 10; rep++) {
            updateSetup(8);
            String expression = summer.getExpression();
            int[] numMap = summer.getNumMap();
            System.out.println("          rep " + rep + "     expression = " + summer.getExpression());
            assertEquals(expression.substring(0, 1), Integer.toString(numMap[1]));
            assertEquals(expression.substring(2),             Integer.toString(numMap[2]));
            summer.clear();
        }
        summer.setEvent(0);

        System.out.println("    - testUpdate8 passed\n");
    }


    @Test
    public void testUpdate9() {

        updateSetup(9);
        assertTrue(summer.isAsleep());
        assertEquals("1+2", summer.getExpression());
        assertEquals(3.0, summer.getResult(), delta);
        assertFalse(summer.getEvent() == 9);
        assertEquals(summer.getReply(), Summer.SLEEP);

        summer.becomeLonger("1");
        summer.update();
        assertEquals(summer.getReply(), Summer.SLEEPY_SLEEPY);

        summer.wakeUp();
        assertEquals(summer.getReply(), Summer.WAKEUP);
        assertFalse(summer.isAsleep());
        System.out.println("    - testUpdate9 passed\n");
    }

    @Test
    public void testUpdate10Success() {
        updateSetup(10);

        assertEquals(summer.getReply(), Summer.PERFORMANCE_REVIEW);
        assertTrue(summer.getEvent() == 10);    // event 10 doesn't changed until next update

        summer.clear();
        summer.becomeLonger("1");
        summer.becomeLonger("0");
        summer.update();


        assertEquals(summer.getReply(), Summer.PERFORMANCE_REVIEW_RESPONSE[4]);
        assertFalse(summer.getEvent() == 10);

        System.out.println("    - testUpdate10Success passed\n");

    }

    @Test
    public void testUpdate10Fail() {
        updateSetup(10);

        assertEquals(summer.getReply(), Summer.PERFORMANCE_REVIEW);
        assertTrue(summer.getEvent() == 10);    // event 10 doesn't changed until next update

        summer.clear();
        summer.becomeLonger("1");
        summer.becomeLonger("+");
        summer.becomeLonger("1");
        summer.update();

        assertEquals(summer.getReply(), Summer.PERFORMANCE_REVIEW_WRONG);

        System.out.println("    - testUpdate10Fail passedn\n");
    }
}
