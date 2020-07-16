package com.starchee.calculator.domain;

public class Main {
    public static String calculate(String line) {

        Converter converter = new Converter();
        Validator validator = new Validator();
        Calculator calculator = new Calculator(validator, converter);
        return calculator.calculate(line);

    }
}
