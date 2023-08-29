package main.ui;

import main.model.Calculator;

/* Terminal based interface for calculator.
 * 
 */
public class TerminalCalculator {
    // member variables
    private Calculator calculator;


    // methods
    public TerminalCalculator() {
        this.calculator = new Calculator();
        runProgram();
    }

    private void runProgram() {
        bootUp();
    }

    private void bootUp() {
        
    }
}
