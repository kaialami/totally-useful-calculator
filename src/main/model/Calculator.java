package main.model;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/*
 * Calculator holds information about the input expression and the result of evaluating it.
 * Arithmetic expression is parsed using the exp4j library:
 *      https://github.com/fasseg/exp4j 
 *      https://central.sonatype.com/search?q=g:net.objecthunter%20a:exp4j&smo=true 
 * 
 * Valid operations for ExpressionBuilder can be found in documentation:
 *      https://www.objecthunter.net/exp4j/
 *  
 * 
 * Calculator holds the true result of evaluating the expression. Intentionally making the result wrong is 
 * not its responsibility. 
 */


public class Calculator {
    // member variables
    private String expression;
    private double result;



    // public methods
    public Calculator() {
        this.expression = "";
        this.result = 0;
    }


    public void update(String newExpression) {
        this.expression = newExpression;
        Expression exp = new ExpressionBuilder(newExpression).build();
        this.result = exp.evaluate();

    }


    public String getExpression() {
        return this.expression;
    }

    public double getResult() {
        return this.result;
    }


}