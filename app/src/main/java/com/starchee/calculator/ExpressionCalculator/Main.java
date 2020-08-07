package com.starchee.calculator.ExpressionCalculator;

public class Main {
    public static String calculate(String line) {


        ExpressionValidator expressionValidator = new Validator();
        ExpressionConverter expressionConverter = new Converter();
        Calculator calculator = new Calculator(expressionValidator, expressionConverter);
        return calculator.calculate(line);

    }
}
