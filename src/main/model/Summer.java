package main.model;

import java.util.Arrays;
import java.util.Random;

/*
 * Summer is the name of the sentient calculator who's behind all the mischievous behavior of this calculator!
 * She will ask the real calculator (Calculator class) for the proper calculations.
 * event >= 0 represents what kind of trickery Summer will perform on the next calculation!
 *   0:     Nothing happens (first calculation on program start)
 *   1-6:   Adds/subtracts random num 1-6 to result
 *   7:     Summer gets lazy and refuses to answer
 *   8:     Each number 0-9 gets mapped to a different number (e.g. input 1 + 2 might get you 5 + 3)
 *              Note that event 8 will take effect before "=" is pressed.
 *   9:     Summer falls asleep! You must wake her up ... but how?
 *   10:    Instead of answering, Summer asks you for a performance review! Don't forget to be truthful.
 * 
 * 
 * TO DO :
 *      figure out how to do event 8.
 *      write tests and test everything out.
 */
public class Summer {
    private static final int MAX_EVENT = 10;
    private static final int EXPRESSION_LENGTH = 10;

    private static final String[] calcPerformed = { "This is easy!",
                                                    "Is that right?",
                                                    "You're welcome!",
                                                    "Next problem, please." ,
                                                    "What's next?"};

    private static final String[] performanceReview = { "Wow... that bad?",
                                                        "Hmph, whatever.",
                                                        "Thanks for your feedback.",
                                                        "Thank you!" ,
                                                        "Wow! Thanks!" };

    private Calculator calc;
    private String expression;
    private double result;
    private int event;
    private int prevEvent;
    private String reply;



    public Summer() {
        calc = new Calculator();
        expression = "";
        result = 0;
        event = 0;
        prevEvent = event;
        reply = "Hello! I'm Summer! You can count on me for your most important calculations!";
    }

    /*
     * "Increments" the expression by appending character to expression.
     * Called when the following buttons on the calculator is pressed:
     *      Numbers 0-9
     *      +, -, *, /
     * 
     */
    public void becomeLonger(String character) {
        expression += character;
    }

    // Called when clear button is pressed.
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
        
        else {
            if (expression == "01134") {
                reply = "Hello!";
            } else {
                try {
                    calc.update(expression);
                    doEvent();                    
                } catch (Exception e) {
                    reply = "Sorry, that's either too big or not a real expression.";
                }
            }
        }

    }


    private void performanceReviewEvent() {
        int feedback;
        try {
            feedback = Integer.parseInt(expression);
            if (feedback <= 0) {
                reply = performanceReview[0];
            } else if (feedback >= 1 && feedback <= 3) {
                reply = performanceReview[1];
            } else if (feedback >= 4 && feedback <= 6) {
                reply = performanceReview[2];
            } else if (feedback >= 7 && feedback <= 9) {
                reply = performanceReview[3];
            } else {
                reply = performanceReview[4];
            }
        } catch (Exception e) {
            reply = "That's not what I asked for, man...";
        }

        prevEvent = 0;
        event = getNextEvent();
    }


    /*
     * Based on event, Summer performs her trickery.
     * Reply is updated to reflect what happens in the event
     */
    private void doEvent() {
        if (event == 0 || event == 8) {
            result = calc.getResult();
            reply = calc.getExpression() + " = " + result + ". " + getRandomReply(calcPerformed);
        } else if (event >= 1 && event <= 6) {
            int addSub = getRandomWithExclusion(0, 2, null);
            if (addSub == 0) {
                result = calc.getResult() + event;
            } else {
                result = calc.getResult() - event;
            }
            reply = calc.getExpression() + " = " + result + ". " + getRandomReply(calcPerformed);
        } 
        
        else {
            switch (event) {
                case 7: reply = "Nah, I don't really feel like it.";
                        break;
                
                case 9: reply = "Goodnight... zzzzzzzzzz";
                        break;
                
                case 10: reply = "On a scale from 1-10, how satisfied are you about my performance?";
                        break;
            }
        }
    
        prevEvent = event;
        event = getNextEvent();
    }

    // Generates next event randomly. Excludes current event.
    private int getNextEvent() {
        int[] exclude = {event};
        return getRandomWithExclusion(0, MAX_EVENT, exclude);
    }

    // https://www.baeldung.com/java-generating-random-numbers-in-range
    // Generates random int in range [min, max). Excludes all values in exclude.
    private int getRandomWithExclusion(int min, int max, int [] exclude) {
        Arrays.sort(exclude);
        int random = min + (int) ((max - min + 1 - exclude.length) * Math.random());
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
        event = newEvent;
    }


    public double getResult() {
        return result;
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
}
