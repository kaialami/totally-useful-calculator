package main.model;


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
        this.result = Double.parseDouble(newExpression);
    }


    public String getExpression() {
        return this.expression;
    }

    public double getResult() {
        return this.result;
    }





}