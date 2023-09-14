package main.model;

import java.util.Arrays;
import java.util.Random;

/*
 * Summer is the name of the sentient calculator who's behind all the mischievous behavior of this calculator!
 * She will ask the real calculator (Calculator class) for the proper calculations.
 * event >= 0 represents what kind of trickery Summer will perform on the next calculation!
 *   0:     Nothing happens (first calculation on program start)
 *   1-6:   Adds random num 1-6 to result
 *   7:     Summer gets lazy and refuses to answer
 *   8:     Each number 0-9 gets mapped to a different number (e.g. input 1 + 2 might get you 5 + 3)
 *              Note that event 8 will take effect before "=" is pressed.
 *   9:     Summer falls asleep! You must wake her up ... but how?
 *   10:    Instead of answering, Summer asks you for a performance review! Don't forget to be truthful.
 * 
 * Legal inputs to calculator:
 * { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
     "+", "-", "*", "/", "(", ")", "."};
 * 
 * TO DO :
 */
public class Summer {
    // constants
    public static final int MAX_EVENT = 10;
    public static final int EXPRESSION_LENGTH = 10;

    public static final int[] DEFAULT_MAP = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static final String GREETING = "Hello! I'm Summer! You can count on me for your most important calculations!";

    public static final String[] CALCPERFORMED = { "This is easy!",
                                                    "Is that right?",
                                                    "You're welcome!",
                                                    "Next problem, please." ,
                                                    "What's next?"};

    public static final String PERFORMANCE_REVIEW = "On a scale from 1-10, how satisfied are you about my performance?";
    public static final String[] PERFORMANCE_REVIEW_RESPONSE = {    "Wow... that bad?",
                                                                    "Hmph, whatever.",
                                                                    "Thanks for your feedback.",
                                                                    "Thank you!" ,
                                                                    "Wow! Thanks!" };
    public static final String PERFORMANCE_REVIEW_WRONG = "That's not what really what I was asking for...";

    public static final String REFUSE = "Nah, I don't really feel like it.";

    public static final String SLEEP = "Goodnight... zzzzzzzzzz";
    public static final String SLEEPY_SLEEPY = "zzzzzzzzzzzzzz......";
    public static final String WAKEUP = "Wah!";

    public static final String HELLO = "Hello!";

    public static final String ERROR = "Sorry, that's either too big or not a real expression.";


    // private member variables
    private Calculator calc;
    private String expression;
    private double result;
    private String outputResult;
    private int event;
    private int prevEvent;
    private String reply;
    private int[] numMap;
    private boolean asleep;



    public Summer() {
        calc = new Calculator();
        expression = "";
        result = 0;
        outputResult = "";
        event = 0;
        prevEvent = event;
        reply = GREETING;
        numMap = DEFAULT_MAP;
        asleep = false;
    }

    /*
     * "Increments" the expression by appending character to expression.
     * Called when the following buttons on the calculator is pressed:
     *      Numbers 0-9
     *      +, -, *, /
     *      (, ), .
     * Does nothing if expression.length >= EXPRESSION_LENGTH.
     * becomeLonger should not be called with invalid inputs.
     *      so assumes if Integer.parseInt(character) fails, the input is still a legal input.
     * character must be of length 1.
     * 
     * Number inputs are read from numMap
     */
    public void becomeLonger(String character) {
        if (expression.length() < EXPRESSION_LENGTH) {
            try {
                int inputInt = Integer.parseInt(character);

                // if character is number
                expression = expression + Integer.toString(numMap[inputInt]);
            } catch (Exception e) {
                // if character is operation / decimal / parantheses.
                expression = expression + character;
            }
        }
    }

    // Called when clear button is pressed.
    // Should be called once "=" is pressed (clear() isn't called in update() so update() is easier to test)
    public void clear() {
        expression = "";
    }

    /*
     * Gives calculator expression to calculate.
     * update is only called once the "=" button is pressed on the calculator.
     * Calls helper function to perform event. Then event is reassigned.
     * Summer says hello back to you!
     * 
     * If prevEvent == 10, instead of calculating, Summer will respond based on your feedback.
     */ 
    public void update() {
        if (prevEvent == 10) {
            performanceReviewEvent();
        } 

        else if (asleep) {
            reply = SLEEPY_SLEEPY;
        }
        
        else {
            if (expression == "01134") {
                reply = HELLO;
            } else {
                try {
                    calc.update(expression);
                    doEvent();                    
                } catch (Exception e) {
                    reply = ERROR;
                }
            }
        }

    }


    private void performanceReviewEvent() {
        int feedback;
        try {
            feedback = Integer.parseInt(expression);
            if (feedback <= 0) {
                reply = PERFORMANCE_REVIEW_RESPONSE[0];
            } else if (feedback >= 1 && feedback <= 3) {
                reply = PERFORMANCE_REVIEW_RESPONSE[1];
            } else if (feedback >= 4 && feedback <= 6) {
                reply = PERFORMANCE_REVIEW_RESPONSE[2];
            } else if (feedback >= 7 && feedback <= 9) {
                reply = PERFORMANCE_REVIEW_RESPONSE[3];
            } else {
                reply = PERFORMANCE_REVIEW_RESPONSE[4];
            }
        } catch (Exception e) {
            reply = PERFORMANCE_REVIEW_WRONG;
        }

        prevEvent = 0;
        event = getNextEvent();
    }


    /*
     * Based on event, Summer performs her trickery.
     * Reply is updated to reflect what happens in the event.
     * 
     * If event = 8 after generating next event, shuffles numMap for the next calc.
     */
    private void doEvent() {
        result = calc.getResult();
        if (event == 0) {
            changeOutputResult();
            reply = calc.getExpression() + " = " + outputResult + ". " + getRandomReply(CALCPERFORMED);
        } else if (event >= 1 && event <= 6) {
            result += event;
            changeOutputResult();
            reply = calc.getExpression() + " = " + outputResult + ". " + getRandomReply(CALCPERFORMED);
        } 
        
        else {
            switch (event) {
                // Refuses to calculate
                case 7: reply = REFUSE;
                        break;

                // Calculates as normal (event affects user as they input characters). Resets numMap.
                case 8: changeOutputResult();
                        reply = calc.getExpression() + " = " + outputResult + ". " + getRandomReply(CALCPERFORMED);
                        numMap = DEFAULT_MAP;
                        break;

                // Falls asleep. Requires action to wake up (cick fog horn)
                case 9: reply = SLEEP;
                        asleep = true;
                        break;
                
                // Instead of calculating, takes next input as a rating.
                case 10: reply = PERFORMANCE_REVIEW;
                        break;
            }
        }
    
        prevEvent = event;
        event = getNextEvent();

        if (event == 8) {
            shuffleNumMap();
        }
    }

    // Shuffles numMap so that numMap[i] may or may not be equal to i.
    // https://www.digitalocean.com/community/tutorials/shuffle-array-java
    public void shuffleNumMap() {
        Random rand = new Random();
		
		for (int i = 0; i < numMap.length; i++) {
			int randomIndexToSwap = rand.nextInt(numMap.length);
			int temp = numMap[randomIndexToSwap];
			numMap[randomIndexToSwap] = numMap[i];
			numMap[i] = temp;
		}
    }

    public void wakeUp() {
        reply = WAKEUP;
        asleep = false;
    }

    private void changeOutputResult() {
        if (doubleEqualsInt(result, getResultInt())) {
            outputResult = Integer.toString(getResultInt());
        } else {
            outputResult = Double.toString(result);
        }
    }

    private boolean doubleEqualsInt(double d, int i) {
        return d == (double) i;
    }


    // Generates next event randomly. Excludes current event.
    private int getNextEvent() {
        int[] exclude = {event};
        return getRandomWithExclusion(0, MAX_EVENT, exclude, 1);
    }

    // https://www.baeldung.com/java-generating-random-numbers-in-range
    // Generates random int in range [min, max). Excludes all values in exclude.
    private int getRandomWithExclusion(int min, int max, int [] exclude, int excludeLength) {
        Arrays.sort(exclude);
        int random = min + (int) ((max - min + 1 - excludeLength) * Math.random());
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }

    private String getRandomReply(String[] replies) {
        int rnd = new Random().nextInt(replies.length);
        return replies[rnd];
    }


    // Should only be used for testing. Allows testing of a specific event without randomness being a pain.
    public void setEvent(int newEvent) {
        if (newEvent == 8) {
            shuffleNumMap();
        } else {
            Arrays.sort(numMap);
        }
        event = newEvent;
    }


    public double getResult() {
        return result;
    }

    public int getResultInt() {
        return (int) result;
    }

    public String getExpression() {
        return expression;
    }

    public String getReply() {
        return reply;
    }

    public int getEvent() {
        return event;
    }

    public int getPrevEvent() {
        return prevEvent;
    }

    public Calculator getCalculator() {
        return calc;
    }

    public int[] getNumMap() {
        return numMap;
    }
}
