package main.ui;

import java.util.Scanner;

import main.model.Calculator;

/* Terminal based interface for calculator. This one doesn't mess with you.
 * 
 * Terminal I/O - https://www.geeksforgeeks.org/ways-to-read-input-from-console-in-java/ 
 */
public class TerminalCalculator {
    // member variables
    private Calculator calculator;
    private Scanner scan;

    // methods
    public TerminalCalculator() {
        initVars();
        runProgram();
    }

    private void initVars() {
        this.calculator = new Calculator();
        this.scan = new Scanner(System.in);
    }

    private void runProgram() {
        bootUp();

        requestExpression();
    }

    private void returnResult() {
        String outputResult;
        if ((double) calculator.getResultInt() == calculator.getResult()) {
            outputResult = Integer.toString(calculator.getResultInt());
        } else {
            outputResult = Double.toString(calculator.getResult());
        }
        System.out.println("= " + outputResult);

    }

    private void requestExpression() {
        System.out.println("What would you like me to calculate?");
        String inputExpression = scan.nextLine();
        try {
            calculator.update(inputExpression);
            returnResult();

        } catch (Exception e) {
            System.out.println("Sorry, that's not something I know how to calculate...");
        }
        
    }

    private void bootUp() {
        System.out.println("Starting calculator program...");
    }
}
