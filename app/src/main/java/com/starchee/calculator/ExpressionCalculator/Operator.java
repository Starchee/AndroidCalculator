package com.starchee.calculator.ExpressionCalculator;

import java.util.HashMap;
import java.util.Map;

public enum Operator {

    ADD("+") {
        @Override
        public double apply(double num1, double num2) {
            return num1 + num2;
        }

        @Override
        public int getPriority() {
            return 0;
        }
    },
    SUBTRACT("-") {
        @Override
        public double apply(double num1, double num2) {
            return num1 - num2;
        }

        @Override
        public int getPriority() {
            return 0;
        }
    },
    MULTIPLY("×") {
        @Override
        public double apply(double num1, double num2) {
            return num1 * num2;
        }

        @Override
        public int getPriority() {
            return 1;
        }
    },
    DIVIDE("÷") {
        @Override
        public double apply(double num1, double num2) {
            return num1 / num2;
        }

        @Override
        public int getPriority() {
            return 1;
        }
    };

    private final String token;

    Operator(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public abstract double apply(double x1, double x2);

    public abstract int getPriority();

    private static final Map<String, Operator> map;

    static {
        map = new HashMap<>();
        for (Operator operator : Operator.values()) {
            map.put(operator.getToken(), operator);
        }
    }

    public static boolean isOperator(String value) {
        return map.containsKey(value);
    }

    public static Operator getOperator(String operatorText) {
        return  map.get(operatorText);
    }

}
